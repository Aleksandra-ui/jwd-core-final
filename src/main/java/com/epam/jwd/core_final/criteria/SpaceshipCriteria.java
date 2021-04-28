package com.epam.jwd.core_final.criteria;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

	public SpaceshipCriteria(Stream<Spaceship> found) {
		super(found);
		
	}

	public Criteria<Spaceship> addCrew(List<Long>crew) {
		setFound(getFound().filter(e -> crew.contains(e.getId())));
		return this;
	}
	
	public Criteria<Spaceship> addDistance(Long distance) {
		setFound(getFound().filter(e -> e.getFlightDistance().equals(distance)));
		return this;
	}
	
	public Criteria<Spaceship> addReadyForNextMissions(Boolean isReadyForNextMissions) {
		setFound(getFound().filter(e -> e.getIsReadyForNextMissions().equals(isReadyForNextMissions)));
		return this;
	}

}
