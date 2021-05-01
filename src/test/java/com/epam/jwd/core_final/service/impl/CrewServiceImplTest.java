package com.epam.jwd.core_final.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidOperationException;

@RunWith(MockitoJUnitRunner.class)
public class CrewServiceImplTest {

	@InjectMocks
	CrewServiceImpl crewService = CrewServiceImpl.newInstance();
	
	@Mock
	CrewMember crewMember;
	
	@Test(expected = InvalidOperationException.class)
	public void testAssignCrewMemberOnMission_returnsNothing() {
		crewService.assignCrewMemberOnMission(crewMember);
	}
	
}
