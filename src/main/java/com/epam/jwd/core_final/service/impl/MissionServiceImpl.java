package com.epam.jwd.core_final.service.impl;

import java.util.List;
import java.util.Optional;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;

public enum MissionServiceImpl implements MissionService {
	
	INSTANCE;

	private static Long id = 1L;
	
	@Override
	public List<FlightMission> findAllMissions() {
		
		return null;
	}

	@Override
	public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
		
		return null;
	}

	@Override
	public FlightMission createMission(FlightMission flightMission) {
	
		flightMission.setId(id++);
		return flightMission;
		
	}

	
	
}
