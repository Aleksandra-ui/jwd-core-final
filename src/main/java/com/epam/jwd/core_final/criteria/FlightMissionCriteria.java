package com.epam.jwd.core_final.criteria;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

	public FlightMissionCriteria(Stream<FlightMission> found) {
		super(found);
		// TODO Auto-generated constructor stub
	}
	
	public Criteria<FlightMission> addStartDate(LocalDate date) {
		setFound(getFound().filter(e -> e.getStartDate().equals(date)));
		return this;
	}
	
	public Criteria<FlightMission> addEndDate(LocalDate date) {
		setFound(getFound().filter(e -> e.getEndDate().equals(date)));
		return this;
	}
	
	public Criteria<FlightMission> addMissionsDistance(Long distance) {
		setFound(getFound().filter(e -> e.getMissionsDistance().equals(distance)));
		return this;
	}
	
	public Criteria<FlightMission> addAssignedSpaceship(Spaceship spaceship) {
		setFound(getFound().filter(e -> e.getAssignedSpaceShip().equals(spaceship)));
		return this;
	}
	
	public Criteria<FlightMission> addAssignedCrew(List<Long> crew) {
		setFound(getFound().filter(e -> crew.contains(e.getId())));
		return this;
	}
	
	public Criteria<FlightMission> addMissionResult(MissionResult result) {
		setFound(getFound().filter(e -> e.getMissionResult().equals(result)));
		return this;
	}
	
	public Criteria<FlightMission> addFrom(Planet from) {
		setFound(getFound().filter(e -> e.getFrom().equals(from)));
		return this;
	}
	
	public Criteria<FlightMission> addTo(Planet to) {
		setFound(getFound().filter(e -> e.getTo().equals(to)));
		return this;
	}
}
