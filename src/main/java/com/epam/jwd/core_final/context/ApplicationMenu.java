package com.epam.jwd.core_final.context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

	static final NassaContext nassaContext = NassaContext.newInstance();
	static final CrewService crewService = CrewServiceImpl.INSTANCE;
	static final SpaceshipService spaceshipService = SpaceshipServiceImpl.INSTANCE;
	static final MissionService missionService = MissionServiceImpl.INSTANCE;

	ApplicationContext getApplicationContext();

	default ApplicationMenu printAvailableOptions() throws InvalidStateException {
		System.out.println("print 1 to see all crew members\n"
				+ "to find crew members by criteria,print 2,criteria(id,name,role,rank,ready_for_next_mission) and its value(for role and rank enter their id)\n"
				+ "to assign crew member on a mission,print 3 and crew member's id\n" + "to create a mission,print 4\n"
				+ "print 5 to see all missions\n"
				+ "to find a mission by criteria,print 6,criteria and its value(id,name,crew,spaceship,start_date,end_date,result,distance(for spaceship,crew members,from and to planets enter their ids,data format:year-month-day)\n"
				+ "to assign a spaceship on a mission,print 7 and spaceship's id\n" 
				+ "to find a spaceship by criteria,print 8,criteria(id,name,crew,distance,ready_for_next_mission)and its value(for crew enter roles through comma)\n"
				+ "print 9 to see all spaceships\n");
		final NassaContext nassaContext = NassaContext.newInstance();
		final Supplier<ApplicationContext> applicationContextSupplier = () -> nassaContext;

		return applicationContextSupplier::get;

	}

	default void handleUserInput() throws InvalidStateException, JsonMappingException, IOException {

		Scanner sc = new Scanner(System.in);
		switch (sc.next()) {
		case "1":
			System.out.println(nassaContext.retrieveBaseEntityList(CrewMember.class));
			break;
		case "2":
			printCrewMembersByCriteria(sc.next(), sc.next());
			break;
		case "3":
			CrewMember member = crewService.findCrewMemberByCriteria(
					new CrewMemberCriteria(nassaContext.retrieveBaseEntityList(CrewMember.class).stream())
							.addId(Long.valueOf(sc.next())))
					.get();
			crewService.assignCrewMemberOnMission(member);
			break;
		case "4":
			nassaContext.writeMission();
			break;
		case "5":
			System.out.println(nassaContext.retrieveBaseEntityList(FlightMission.class));
			break;
		case "6":
			printMissionsByCriteria(sc.next(), sc.next());
			break;
		case "7":
			Spaceship ship = spaceshipService.findSpaceshipByCriteria(
					new SpaceshipCriteria(nassaContext.retrieveBaseEntityList(Spaceship.class).stream())
							.addId(Long.valueOf(sc.next())))
					.get();
			spaceshipService.assignSpaceshipOnMission(ship);
			break;
		case "8":
			printSpaceshipsByCriteria(sc.next(), sc.next());
			break;
		case "9":
			System.out.println(nassaContext.retrieveBaseEntityList(Spaceship.class));
			break;
		}
	}

	default void printCrewMembersByCriteria(String criteria, String value) {

		List<CrewMember> members = null;

		switch (criteria) {

		case "role":
			members = crewService.findAllCrewMembersByCriteria(
					new CrewMemberCriteria(nassaContext.retrieveBaseEntityList(CrewMember.class).stream())
							.addRole(Role.resolveRoleById(Integer.valueOf(value))));
			break;

		case "id":
			members = crewService.findAllCrewMembersByCriteria(
					new CrewMemberCriteria(nassaContext.retrieveBaseEntityList(CrewMember.class).stream())
							.addId(Long.valueOf(value)));
			break;
		case "name":
			members = crewService.findAllCrewMembersByCriteria(
					new CrewMemberCriteria(nassaContext.retrieveBaseEntityList(CrewMember.class).stream())
							.addName(value));
			break;
		case "rank":
			members = crewService.findAllCrewMembersByCriteria(
					new CrewMemberCriteria(nassaContext.retrieveBaseEntityList(CrewMember.class).stream())
							.addRank(Rank.resolveRankById(Integer.valueOf(value))));
			break;
		case "ready_for_next_mission":
			members = crewService.findAllCrewMembersByCriteria(
					new CrewMemberCriteria(nassaContext.retrieveBaseEntityList(CrewMember.class).stream())
							.addReadyForNextMissions(Boolean.valueOf(value)));
			break;

		}

		System.out.println(members);

	}

	default void printMissionsByCriteria(String criteria, String value) {

		List<FlightMission> missions = null;
		switch (criteria) {
		case "id":
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addId(Long.valueOf(value)));
			break;
		case "name":
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addName(value));
			break;
		case "crew":
			List<String> crew = Arrays.asList(value.split(","));
			List<Long> crew2 = new ArrayList<>();
			crew.forEach(m -> crew2.add(Long.valueOf(m)));
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addAssignedCrew(crew2));
			break;
		case "spaceship":
			Spaceship ship = spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria(nassaContext.retrieveBaseEntityList(Spaceship.class).stream()).addId(Long.valueOf(value))).get();
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addAssignedSpaceship(ship));
			break;
		case "start_date":
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addStartDate(LocalDate.parse(value)));
			break;
		case "end_date":
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addEndDate(LocalDate.parse(value)));
		case "result":
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addMissionResult(MissionResult.valueOf(value)));
			break;
		case "distance":
			missions = missionService.findAllMissionsByCriteria(
					new FlightMissionCriteria(nassaContext.retrieveBaseEntityList(FlightMission.class).stream())
							.addMissionsDistance(Long.valueOf(value)));
			break;
					
		}
		System.out.println(missions);

	}

	default void printSpaceshipsByCriteria(String criteria, String value) {

		List<Spaceship> ships = null;
		switch (criteria) {
		case "id":
			ships = spaceshipService.findAllSpaceshipsByCriteria(
					new SpaceshipCriteria(nassaContext.retrieveBaseEntityList(Spaceship.class).stream())
							.addId(Long.valueOf(value)));
			break;
		case "name":
			ships = spaceshipService.findAllSpaceshipsByCriteria(
					new SpaceshipCriteria(nassaContext.retrieveBaseEntityList(Spaceship.class).stream())
							.addName(value));
			break;
		case "crew":
			List<String> crew = Arrays.asList(value.split(","));
			List<Long> crew2 = new ArrayList<>();
			crew.forEach(m -> crew2.add(Long.valueOf(m)));
			ships = spaceshipService.findAllSpaceshipsByCriteria(
					new SpaceshipCriteria(nassaContext.retrieveBaseEntityList(Spaceship.class).stream())
							.addCrew(crew2));
			break;
		case "distance":
			ships = spaceshipService.findAllSpaceshipsByCriteria(
					new SpaceshipCriteria(nassaContext.retrieveBaseEntityList(Spaceship.class).stream())
							.addDistance(Long.valueOf(value)));
			break;
		case "ready_for_next_mission":
			ships = spaceshipService.findAllSpaceshipsByCriteria(
					new SpaceshipCriteria(nassaContext.retrieveBaseEntityList(Spaceship.class).stream())
							.addReadyForNextMissions(Boolean.valueOf(value)));
		}
		System.out.println(ships);

	}
}
