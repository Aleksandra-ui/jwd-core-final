package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String} start date {@link java.time.LocalDate} end date
 * {@link java.time.LocalDate} distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default assignedCrew
 * {@link java.util.List<CrewMember>} - list of missions members based on ship
 * capacity - not defined by default missionResult {@link MissionResult} from
 * {@link Planet} to {@link Planet}
 */
public class FlightMission extends AbstractBaseEntity {

	/*
	 * @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 * 
	 * @JsonSerialize(using = LocalDateTimeSerializer.class)
	 */
	private final LocalDate startDate;
	/*
	 * @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 * 
	 * @JsonSerialize(using = LocalDateTimeSerializer.class)
	 */
	private final LocalDate endDate;
	private final Long missionsDistance;
	private Spaceship assignedSpaceShip;
	private List<CrewMember> assignedCrew;
	private MissionResult missionResult;
	private Planet from;
	private Planet to;

	public FlightMission(String name, LocalDate startDate, LocalDate endDate, Long missionsDistance,
			Spaceship assignedSpaceShip, MissionResult missionResult) {
		super(name);
		this.startDate = startDate;
		this.endDate = endDate;
		this.missionsDistance = missionsDistance;
		this.assignedSpaceShip = assignedSpaceShip;
		this.missionResult = missionResult;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Long getMissionsDistance() {
		return missionsDistance;
	}

	public Spaceship getAssignedSpaceShip() {
		return assignedSpaceShip;
	}

	public void setAssignedSpaceShip(Spaceship spaceShip) {
		assignedSpaceShip = spaceShip;
	}

	public List<CrewMember> getAssignedCrew() {
		return assignedCrew;
	}

	public MissionResult getMissionResult() {
		return missionResult;
	}

	public Planet getFrom() {
		return from;
	}

	public Planet getTo() {
		return to;
	}

	public void setAssignedCrew(List<CrewMember> assignedCrew) {
		this.assignedCrew = assignedCrew;
	}

	public void setAssignedCrewMember(CrewMember assignedCrewMember) {
		if (this.assignedCrew == null) {
			assignedCrew = new ArrayList<>();
		}
		assignedCrew.add(assignedCrewMember);
	}

	public void setMissionResult(MissionResult missionResult) {
		this.missionResult = missionResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assignedCrew == null) ? 0 : assignedCrew.hashCode());
		result = prime * result + ((assignedSpaceShip == null) ? 0 : assignedSpaceShip.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((missionResult == null) ? 0 : missionResult.hashCode());
		result = prime * result + (int) (missionsDistance ^ (missionsDistance >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightMission other = (FlightMission) obj;
		if (assignedCrew == null) {
			if (other.assignedCrew != null)
				return false;
		} else if (!assignedCrew.equals(other.assignedCrew))
			return false;
		if (assignedSpaceShip == null) {
			if (other.assignedSpaceShip != null)
				return false;
		} else if (!assignedSpaceShip.equals(other.assignedSpaceShip))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (missionResult != other.missionResult)
			return false;
		if (missionsDistance != other.missionsDistance)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlightMission [id=" + getId() + ", name=" + getName() + "startDate=" + startDate + ", endDate="
				+ endDate + ", missionsDistance=" + missionsDistance + ", assignedSpaceShip=" + assignedSpaceShip
				+ ", assignedCrew=" + assignedCrew + ", missionResult=" + missionResult + ", from=" + from + ", to="
				+ to + "]";
	}

}
