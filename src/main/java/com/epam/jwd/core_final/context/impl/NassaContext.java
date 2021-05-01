package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;

import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// todo
public class NassaContext implements ApplicationContext {

	// no getters/setters for them
	private List<CrewMember> crewMembers = new ArrayList<>();
	private List<Spaceship> spaceships = new ArrayList<>();
	private List<Planet> planetMap = new ArrayList<>();
	private List<FlightMission> missions = new ArrayList<>();

	private CrewMemberFactory crewMemberFactory = CrewMemberFactory.INSTANCE;
	private SpaceshipFactory spaceshipFactory = SpaceshipFactory.INSTANCE;
	private static final Logger logger = LoggerFactory.getLogger(NassaContext.class);

	private static NassaContext instance;

	private NassaContext() {
	}

	public static NassaContext newInstance() {
		if (instance == null) {
			instance = new NassaContext();
		}
		return instance;
	}

	@Override
	public <T extends BaseEntity> List retrieveBaseEntityList(Class<T> tClass) {
		if (CrewMember.class.isAssignableFrom(tClass)) {
			return crewMembers;
		}

		if (Spaceship.class.isAssignableFrom(tClass)) {
			return spaceships;
		}

		if (Planet.class.isAssignableFrom(tClass)) {
			return planetMap;
		}

		if (FlightMission.class.isAssignableFrom(tClass)) {
			return missions;
		}

		return null;
	}

	/**
	 * You have to read input files, populate collections
	 * 
	 * @throws InvalidStateException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	@Override
	public void init() throws InvalidStateException, FileNotFoundException, UnsupportedEncodingException {
		PropertyReaderUtil.loadProperties();
		String inputDir = ApplicationProperties.getInstance().getInputRootDir();
		String outputDir = ApplicationProperties.getInstance().getOutputRootDir();

		readCrew(inputDir);
		logger.info("crew members read from file");
		readShips(inputDir);
		logger.info("spaceships read from file");
		readMissions(outputDir);
		logger.info("missions read from file");

	}

	private void readCrew(String inputDir) throws NumberFormatException {

		String crewFileName = ApplicationProperties.getInstance().getCrewFileName();
		long i = 1;

		try {

			String crew = new String(Files.readAllBytes(Paths.get(inputDir + "/" + crewFileName)),
					StandardCharsets.UTF_8);
			@SuppressWarnings("resource")
			Scanner in = new Scanner(crew);
			in.nextLine();
			String crew2 = in.nextLine();
			String[] members = crew2.split(";");
			for (String member : members) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(member);
				sc.useDelimiter(",");
				crewMembers.add(crewMemberFactory.create(i++, Integer.valueOf(sc.next()), sc.next(),
						Integer.valueOf(sc.next())));

			}

		} catch (IOException | InvalidStateException e) {
			e.printStackTrace();
		}

	}

	private void readShips(String inputDir) {

		String shipsFileName = ApplicationProperties.getInstance().getSpaceshipsFileName();
		long id = 1;
		String name;
		Long distance;

		String[] members;
		Map<Role, Short> crew;
		Spaceship spaceship;
		String[] charasteristics;

		try {
			String ships = new String(Files.readAllBytes(Paths.get(inputDir + "/" + shipsFileName)),
					StandardCharsets.UTF_8);
			@SuppressWarnings("resource")
			Scanner line = new Scanner(ships);
			line.nextLine();
			line.nextLine();
			line.nextLine();
			String ship = line.nextLine();

			while (line.hasNextLine()) {
				charasteristics = ship.split(";");
				name = charasteristics[0];
				distance = Long.valueOf(charasteristics[1]);
				members = charasteristics[2].substring(1, charasteristics[2].length() - 1).split(",");
				crew = new HashMap<>();
				for (String member : members) {
					crew.put(Role.resolveRoleById(Integer.valueOf(member.substring(0, 1))),
							Short.valueOf(member.substring(2, 3)));
				}

				spaceship = spaceshipFactory.create(id++, name, crew, distance);
				spaceships.add(spaceship);

				ship = line.nextLine();
			}
			charasteristics = ship.split(";");
			name = charasteristics[0];
			distance = Long.valueOf(charasteristics[1]);
			members = charasteristics[2].substring(1, charasteristics[2].length() - 1).split(",");
			crew = new HashMap<>();
			for (String member : members) {
				crew.put(Role.resolveRoleById(Integer.valueOf(member.substring(0, 1))),
						Short.valueOf(member.substring(2, 3)));
			}
			spaceship = spaceshipFactory.create(id++, name, crew, distance);
			spaceships.add(spaceship);

		} catch (IOException | InvalidStateException e) {
			e.printStackTrace();

		}

	}

	private void readPlanets(String inputDir) {

		String spacemapFileName = ApplicationProperties.getInstance().getSpacemapFileName();

		try {
			String planets = new String(Files.readAllBytes(Paths.get(inputDir + "/" + spacemapFileName)),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readMissions(String outputDir) {

		String missionsFileName = ApplicationProperties.getInstance().getMissionsFileName();

		/*
		 * try { ObjectMapper mapper = new ObjectMapper(); mapper.registerModule(new
		 * JavaTimeModule()); File is = new File(outputDir + "/"
		 * +missionsFileName+".json"); FlightMission testObj = mapper.readValue(is,
		 * FlightMission.class); missions.add(testObj); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

	public void writeMission() throws InvalidStateException, JsonMappingException, IOException {
		System.out.println("avaliable spaceships:\n" + this.retrieveBaseEntityList(Spaceship.class));
		System.out.println("avaliable crew members:\n" + this.retrieveBaseEntityList(CrewMember.class));
		System.out.println("avaliable planets:\n" + this.retrieveBaseEntityList(Planet.class));
		System.out.println("enter mission's name,spaceship's id and flightmission status");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		Long shipId = Long.valueOf(sc.next());
		MissionResult result = MissionResult.valueOf((sc.next()));
		Spaceship ship = spaceships.stream().filter(sh -> sh.getId().equals(shipId)).findAny().get();

		LocalDate startDate = LocalDate.ofEpochDay(ThreadLocalRandom.current()
				.longs(LocalDate.of(1990, 1, 1).toEpochDay(), LocalDate.now().toEpochDay()).findAny().getAsLong());
		LocalDate endDate = LocalDate.ofEpochDay(ThreadLocalRandom.current()
				.longs(startDate.toEpochDay(), LocalDate.now().toEpochDay()).findAny().getAsLong());
		Long distance = new Random().nextLong();

		FlightMission mission = MissionServiceImpl.INSTANCE
				.createMission(FlightMissionFactory.INSTANCE.create(name, startDate, endDate, distance, ship, result));

		addMissionToFile(mission);
		logger.info("new mission added to file");

	}

	private void addMissionToFile(FlightMission mission) {

		String outputDir = ApplicationProperties.getInstance().getOutputRootDir();
		String missionsFileName = ApplicationProperties.getInstance().getMissionsFileName();

		ObjectMapper om = new ObjectMapper();

		try (FileWriter writer = new FileWriter(outputDir + "/" + missionsFileName + ".json", true)) {

			writer.write(om.writeValueAsString(mission));

			writer.flush();
		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}
		missions.add(mission);

	}

}
