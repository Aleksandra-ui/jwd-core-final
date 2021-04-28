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
import com.epam.jwd.core_final.service.CrewService;

public enum CrewServiceImpl implements CrewService {

	INSTANCE;

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
	public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
		List<FlightMission> missions = NassaContext.newInstance().retrieveBaseEntityList(FlightMission.class);
		if (missions.size() != 0) {
			FlightMission mission = missions.get(new Random().nextInt(missions.size() + 1));
			mission.setAssignedCrewMember(crewMember);
		}
	}

	@Override
	public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
		return null;
	}

}
