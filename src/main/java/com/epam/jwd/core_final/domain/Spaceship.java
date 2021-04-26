package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */

//field Crew:map <Role,NumOfPeople>
public class Spaceship extends AbstractBaseEntity {

	private final Map<Role, Short> crew;
	private final Long flightDistance;
	private Boolean isReadyForNextMissions = true;
	
	public Spaceship(Long id, String name, Map<Role, Short> crew, Long flightDistance) {
		super(id, name);
		this.crew = crew;
		this.flightDistance = flightDistance;
	}

	public Map<Role, Short> getCrew() {
		return crew;
	}

	public Long getFlightDistance() {
		return flightDistance;
	}

	public Boolean getIsReadyForNextMissions() {
		return isReadyForNextMissions;
	}
	
	public void setIsReadyForNextMissions(Boolean IsReadyForNextMissions ) {
		this.isReadyForNextMissions = isReadyForNextMissions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((crew == null) ? 0 : crew.hashCode());
		result = prime * result + ((flightDistance == null) ? 0 : flightDistance.hashCode());
		result = prime * result + ((isReadyForNextMissions == null) ? 0 : isReadyForNextMissions.hashCode());
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
		Spaceship other = (Spaceship) obj;
		if (crew == null) {
			if (other.crew != null)
				return false;
		} else if (!crew.equals(other.crew))
			return false;
		if (flightDistance == null) {
			if (other.flightDistance != null)
				return false;
		} else if (!flightDistance.equals(other.flightDistance))
			return false;
		if (isReadyForNextMissions == null) {
			if (other.isReadyForNextMissions != null)
				return false;
		} else if (!isReadyForNextMissions.equals(other.isReadyForNextMissions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Spaceship [id=" + getId() + ", name=" + getName() +", crew=" + crew + ", flightDistance=" + flightDistance + ", isReadyForNextMissions="
				+ isReadyForNextMissions + "]";
	}
	
}
