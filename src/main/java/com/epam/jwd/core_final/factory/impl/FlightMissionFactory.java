package com.epam.jwd.core_final.factory.impl;

import java.time.LocalDate;
import java.util.List;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public enum FlightMissionFactory implements EntityFactory<FlightMission> {

	INSTANCE;

	@Override
	public FlightMission create(Object... args) throws InvalidStateException {

		try {
			
			String name = (String) args[0];
			LocalDate startDate = (LocalDate) args[1];
			LocalDate endDate = (LocalDate) args[2];
			Long missionsDistance = (Long)args[3];
			Spaceship assignedSpaceShip = (Spaceship)args[4];
			
			return new FlightMission( name, startDate,endDate,missionsDistance,assignedSpaceShip);

		} catch (ClassCastException | ArrayIndexOutOfBoundsException e) {
			throw new InvalidStateException("Can not create a flight mission!");
		}

	}
}
