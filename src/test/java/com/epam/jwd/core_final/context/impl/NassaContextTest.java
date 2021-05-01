package com.epam.jwd.core_final.context.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import org.junit.Assert;

@RunWith(JUnit4.class)
public class NassaContextTest {
	
	NassaContext nassaContext = NassaContext.newInstance();

	@Test
	public void testRetrieveBaseEntityList_returnsListOfObjectsOfCertainType_whenThisTypeIsTransfered() {
		try {
			nassaContext.init();
		} catch (FileNotFoundException | UnsupportedEncodingException | InvalidStateException e) {}
		Assert.assertTrue(nassaContext.retrieveBaseEntityList(CrewMember.class).get(0) instanceof CrewMember);
	}
	
	
	
}
