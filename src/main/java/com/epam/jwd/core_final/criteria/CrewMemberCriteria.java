package com.epam.jwd.core_final.criteria;

import java.util.stream.Stream;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

	public CrewMemberCriteria(Stream<CrewMember> found) {
		super(found);
	}
	
	public Criteria<CrewMember> addRole(Role role) {
		setFound ( getFound().filter(e -> e.getRole().equals(role)) );
		return this;
	}
	
	
	
}
