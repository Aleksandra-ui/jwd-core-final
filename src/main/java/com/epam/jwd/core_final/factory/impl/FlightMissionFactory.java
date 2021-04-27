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
			Long id = (Long) args[0];
			String name = (String) args[1];
			LocalDate startDate = (LocalDate) args[2];
			LocalDate endDate = (LocalDate) args[3];
			Long missionsDistance = (Long)args[4];
			Spaceship assignedSpaceShip = (Spaceship)args[5];
			List<CrewMember> assignedCrew = (List<CrewMember>)args[6];
			MissionResult missionResult = (MissionResult)args[7];
			Planet from = (Planet)args[8];
			Planet to = (Planet)args[9];
			return new FlightMission(id, name, startDate,endDate,missionsDistance,assignedSpaceShip,assignedCrew,
					missionResult,from, to);

		} catch (ClassCastException | ArrayIndexOutOfBoundsException e) {
			throw new InvalidStateException("Can not create a flight mission!");
		}

	}
}
