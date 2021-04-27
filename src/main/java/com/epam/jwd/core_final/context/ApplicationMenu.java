package com.epam.jwd.core_final.context;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

	ApplicationContext getApplicationContext();

	default ApplicationMenu printAvailableOptions() throws InvalidStateException {
		System.out.println("you'll do the task.");
		final NassaContext nassaContext = NassaContext.newInstance();
		final Supplier<ApplicationContext> applicationContextSupplier = () -> nassaContext; // todo

		return applicationContextSupplier::get;

	}

	default void handleUserInput() {
		Scanner sc = new Scanner(System.in);
		switch (sc.next()) {
		case "find_crewmember_by_criteria":
			printCrewMembersByCriteria(sc.next(), sc.next());
		}
	}

	default void printCrewMembersByCriteria(String criteria, String value) {

		switch (criteria) {
		case "role":
			Criteria<CrewMember> cr = new CrewMemberCriteria(
					NassaContext.newInstance().retrieveBaseEntityList(CrewMember.class).stream())
							.addRole(Role.resolveRoleById(Integer.valueOf(value)));

			List<CrewMember> crew = CrewServiceImpl.INSTANCE.findAllCrewMembersByCriteria(cr);
			System.out.println(crew);
		}

	}
}
