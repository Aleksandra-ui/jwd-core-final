package com.epam.jwd.core_final.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;

public enum MissionServiceImpl implements MissionService {

	INSTANCE;

	ApplicationContext context = NassaContext.newInstance();

	private static Long id = 1L;

	@Override
	public List<FlightMission> findAllMissions() {

		return (List<FlightMission>) context.retrieveBaseEntityList(FlightMission.class);
	}

	@Override
	public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
		// TODO Auto-generated method stub
		List<FlightMission> flightMissions = findAllMissions();
		flightMissions = criteria.find().collect(Collectors.toList());
		return flightMissions;
	}

	@Override
	public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {

		Optional<FlightMission> flightMission = (Optional<FlightMission>) criteria.find().findAny();
		return flightMission;
	}

	@Override
	public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
		flightMission.getAssignedSpaceShip().setIsReadyForNextMissions(false);
		return flightMission;
	}

	@Override
	public FlightMission createMission(FlightMission flightMission) {

		flightMission.setId(id++);
		return flightMission;

	}

}
