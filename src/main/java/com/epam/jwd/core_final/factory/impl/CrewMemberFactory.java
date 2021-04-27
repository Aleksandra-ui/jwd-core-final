package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public enum CrewMemberFactory implements EntityFactory<CrewMember> {

	INSTANCE;

	@Override
	public CrewMember create(Object... args) throws InvalidStateException {

		try {
			Long id = (Long) args[0];
			Role role = Role.resolveRoleById((Integer) args[1]);
			String name = (String) args[2];
			Rank rank = Rank.resolveRankById((Integer) args[3]);
			return new CrewMember(id, role, name, rank);

		} catch (ClassCastException | ArrayIndexOutOfBoundsException e) {
			throw new InvalidStateException("Can not create a crew member!");
		}

	}
}
