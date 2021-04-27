package com.epam.jwd.core_final.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.CrewService;

public class CrewServiceImpl implements CrewService {

	ApplicationContext context = NassaContext.newInstance();

	@Override
	public List<CrewMember> findAllCrewMembers() {
		
		return (List<CrewMember>) context.retrieveBaseEntityList(CrewMember.class);
	}

	@Override
	public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
		List<CrewMember> crewMembers = findAllCrewMembers();
		crewMembers = criteria.find().collect(Collectors.toList());
		return crewMembers;
	}

	@Override
	public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CrewMember createCrewMember(CrewMember spaceship) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
