
package com.epam.jwd.core_final.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
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
import com.epam.jwd.core_final.service.SpaceshipService;

public enum SpaceshipServiceImpl implements SpaceshipService {

	INSTANCE;

	ApplicationContext context = NassaContext.newInstance();

	@Override
	public List<Spaceship> findAllSpaceships() {
		return (List<Spaceship>) context.retrieveBaseEntityList(Spaceship.class);
	}

	@Override
	public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
		List<Spaceship> spaceships = findAllSpaceships();
		spaceships = criteria.find().collect(Collectors.toList());
		return spaceships;
	}

	@Override
	public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {

		Optional<Spaceship> spaceship = (Optional<Spaceship>) criteria.find().findAny();
		return spaceship;
	}

	@Override
	public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
		spaceship.setIsReadyForNextMissions(false);
		return spaceship;
	}

	@Override
	public void assignSpaceshipOnMission(Spaceship spaceship) throws InvalidOperationException {
		
		if (! spaceship.getIsReadyForNextMissions()) {
			throw new InvalidOperationException("Can not assign the spaceship on a mission, because he is not ready for next missions!");
		}
		List<FlightMission> missions = NassaContext.newInstance().retrieveBaseEntityList(FlightMission.class);
		if (missions.size() != 0) {
		FlightMission mission;
		List<CrewMember> crew;
			
		outer:
			while (!missions.isEmpty()) {
				mission = missions.get(new Random().nextInt(missions.size() + 1) - 1);
				crew = mission.getAssignedCrew();
				Map<Role, List<CrewMember>> crewMembersByRoles = crew.stream().collect(Collectors.groupingBy(
						CrewMember::getRole, Collectors.mapping(Function.identity(), Collectors.toList())));
				if (! spaceship.getCrew().keySet().contains(crewMembersByRoles.keySet()) ) {
					missions.remove(mission);
					continue;
				}
				Map<Role,Short> crewMembersByRolesCount = new HashMap<>();
				crewMembersByRoles.forEach((k,v) -> crewMembersByRolesCount.put(k, (short)v.size()));
				for ( Role role0 : crewMembersByRolesCount.keySet() ) {
					if (crewMembersByRolesCount.get(role0) > spaceship.getCrew().get(role0)) {
						missions.remove(mission);
						continue outer;
					}
				}
					
				mission.setAssignedSpaceShip(spaceship);
				if ( mission.getMissionResult().equals(MissionResult.FAILED)) {
					updateSpaceshipDetails(spaceship);
				}
			}
			
		}
	}

	@Override
	public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
		// TODO Auto-generated method stub
		return spaceship;
	}

}
