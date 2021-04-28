package com.epam.jwd.core_final.factory.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public enum SpaceshipFactory implements EntityFactory<Spaceship> {

	INSTANCE;

	private static final Logger logger = LoggerFactory.getLogger(SpaceshipFactory.class);
	
	@Override
	public Spaceship create(Object... args) throws InvalidStateException {

		try {
			Long id = (Long) args[0];
			String name = (String) args[1];
			Map<Role, Short> crew = (Map<Role, Short>) args[2];
			Long flightDistance = (Long) args[3];
			logger.info("created new spaceship");
			return new Spaceship(id, name, crew, flightDistance);

		} catch (ClassCastException | ArrayIndexOutOfBoundsException e) {
			throw new InvalidStateException("Can not create a flight mission!");
		}

	}
}
