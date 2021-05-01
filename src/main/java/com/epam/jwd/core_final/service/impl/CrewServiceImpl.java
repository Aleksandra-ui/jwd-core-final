package com.epam.jwd.core_final.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidOperationException;
import com.epam.jwd.core_final.service.CrewService;

public class CrewServiceImpl implements CrewService {

	private static CrewServiceImpl instance;

	private CrewServiceImpl() {
	}

	public static CrewServiceImpl newInstance() {
		if (instance == null) {
			instance = new CrewServiceImpl();
		}
		return instance;
	}

	ApplicationContext context = NassaContext.newInstance();

	@Override
	public List<CrewMember> findAllCrewMembers() {

		return (List<CrewMember>) context.retrieveBaseEntityList(CrewMember.class);
	}

	@Override
	public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
		List<CrewMember> crewMembers = findAllCrewMembers();
		crewMembers = criteria.find().collect(Collectors.toList());
		return crewMembers;
	}

	@Override
	public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {

		Optional<CrewMember> crewMember = (Optional<CrewMember>) criteria.find().findAny();
		return crewMember;
	}

	@Override
	public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
		crewMember.setIsReadyForNextMissions(false);
		return crewMember;
	}

	@Override
	public void assignCrewMemberOnMission(CrewMember crewMember) throws InvalidOperationException {
		
		if (! crewMember.getIsReadyForNextMissions()) {
			throw new InvalidOperationException("Can not assign the crew member on a mission, because he is not ready for next missions!");
		}
		List<FlightMission> missions = NassaContext.newInstance().retrieveBaseEntityList(FlightMission.class);
		if (missions.size() != 0) {
			
			FlightMission mission;
			Spaceship spaceship;
			Short maxCount;
			
			while(!missions.isEmpty()){
				mission = missions.get(new Random().nextInt(missions.size() + 1));
				spaceship=mission.getAssignedSpaceShip();
				final Role role=crewMember.getRole();
				
				if (!spaceship.getCrew().keySet().contains(role)) {
					missions.remove(mission);
					continue;
				}
				
				maxCount = spaceship.getCrew().get(role);
				if ( mission.getAssignedCrew().stream().filter(m -> role.equals(m.getRole())).count() < maxCount ) {
					mission.setAssignedCrewMember(crewMember);
					if ( mission.getMissionResult().equals(MissionResult.FAILED)) {
						updateCrewMemberDetails(crewMember);
					}
					break;
				} else {
					missions.remove(mission);
				}
			}
			
		}
	}

	@Override
	public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
		return crewMember;
	}

}
