package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after
 * first failed mission
 */
public class CrewMember extends AbstractBaseEntity {

	private final Role role;
	private final Rank rank;
	private Boolean isReadyForNextMissions = true;

	public CrewMember(Long id, Role role, String name, Rank rank) {
		super(name);
		setId(id);
		this.role = role;
		this.rank = rank;
	}

	public Boolean getIsReadyForNextMissions() {
		return isReadyForNextMissions;
	}

	public void setIsReadyForNextMissions(Boolean isReadyForNextMissions) {
		this.isReadyForNextMissions = isReadyForNextMissions;
	}

	public Role getRole() {
		return role;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((isReadyForNextMissions == null) ? 0 : isReadyForNextMissions.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		CrewMember other = (CrewMember) obj;
		if (isReadyForNextMissions == null) {
			if (other.isReadyForNextMissions != null)
				return false;
		} else if (!isReadyForNextMissions.equals(other.isReadyForNextMissions))
			return false;
		if (rank != other.rank)
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CrewMember [id=" + getId() + ", name=" + getName() + " ,role=" + role + ", rank=" + rank
				+ ", isReadyForNextMissions=" + isReadyForNextMissions + "]";
	}

}