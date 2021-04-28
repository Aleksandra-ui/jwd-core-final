
package com.epam.jwd.core_final.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
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
	public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
		List<FlightMission> missions = NassaContext.newInstance().retrieveBaseEntityList(FlightMission.class);
		if (missions.size() != 0) {
			FlightMission mission = missions.get(new Random().nextInt(missions.size() + 1) - 1);
			mission.setAssignedSpaceShip(spaceship);
		}
	}

	@Override
	public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
		// TODO Auto-generated method stub
		return spaceship;
	}

}
