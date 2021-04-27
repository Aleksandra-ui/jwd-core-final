package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

// todo
public class NassaContext implements ApplicationContext {

	// no getters/setters for them
	private List<CrewMember> crewMembers = new ArrayList<>();
	private List<Spaceship> spaceships = new ArrayList<>();
	private List<Planet> planetMap = new ArrayList<>();
	private List<FlightMission> missions = new ArrayList<>();

	private CrewMemberFactory crewMemberFactory = CrewMemberFactory.INSTANCE;
	private SpaceshipFactory spaceshipFactory = SpaceshipFactory.INSTANCE;

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

		return null;
	}

	/**
	 * You have to read input files, populate collections
	 * 
	 * @throws InvalidStateException
	 */
	@Override
	public void init() throws InvalidStateException {
		PropertyReaderUtil.loadProperties();
		String inputDir = ApplicationProperties.getInstance().getInputRootDir();

		readCrew(inputDir);
		readShips(inputDir);
		writeMission();

	}

	private void readCrew(String inputDir) throws NumberFormatException, InvalidStateException {

		String crewFileName = ApplicationProperties.getInstance().getCrewFileName();
		long i = 1;

		try {
			String crew = new String(Files.readAllBytes(Paths.get(inputDir + "/" + crewFileName)),
					StandardCharsets.UTF_8);
			Scanner in = new Scanner(crew);
			in.nextLine();
			String crew2 = in.nextLine();
			String[] members = crew2.split(";");
			for (String member : members) {
				Scanner sc = new Scanner(member);
				sc.useDelimiter(",");
				crewMembers.add(crewMemberFactory.create(i++, Integer.valueOf(sc.next()), sc.next(),
						Integer.valueOf(sc.next())));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readShips(String inputDir) {

		String shipsFileName = ApplicationProperties.getInstance().getSpaceshipsFileName();
		long id = 1;
		String name;
		Long distance;
		Scanner word;
		String[] members;
		Map<Role, Short> crew;
		Spaceship spaceship;

		try {
			String ships = new String(Files.readAllBytes(Paths.get(inputDir + "/" + shipsFileName)),
					StandardCharsets.UTF_8);
			Scanner line = new Scanner(ships);
			line.nextLine();
			line.nextLine();
			line.nextLine();
			String ship = line.nextLine();

			while (line.hasNextLine()) {
				String[] charasteristics = ship.split(";");
				name = charasteristics[0];
				distance = Long.valueOf(charasteristics[1]);
				members = charasteristics[2].substring(1, charasteristics[2].length() - 1).split(",");
				crew = new HashMap<>();
				for (String member : members) {
					crew.put(Role.resolveRoleById(Integer.valueOf(member.substring(0, 1))),
							Short.valueOf(member.substring(2, 3)));
				}
				try {
					spaceship = spaceshipFactory.create(id++, name, crew, distance);
					spaceships.add(spaceship);
				} catch (InvalidStateException e) {
					e.printStackTrace();
				}

				ship = line.nextLine();
			}
			String[] charasteristics = ship.split(";");
			name = charasteristics[0];
			distance = Long.valueOf(charasteristics[1]);
			members = charasteristics[2].substring(1, charasteristics[2].length() - 1).split(",");
			crew = new HashMap<>();
			for (String member : members) {
				crew.put(Role.resolveRoleById(Integer.valueOf(member.substring(0, 1))),
						Short.valueOf(member.substring(2, 3)));
			}
			spaceship = new Spaceship(id++, name, crew, distance);
			spaceships.add(spaceship);

		} catch (IOException e) {
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

	private void writeMission() throws InvalidStateException {
		System.out.println(this.retrieveBaseEntityList(Spaceship.class));
		System.out.println(this.retrieveBaseEntityList(CrewMember.class));
		System.out.println(this.retrieveBaseEntityList(Planet.class));
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		Integer shipId = Integer.valueOf(sc.next());
		Integer fromId = Integer.valueOf(sc.next());
		Integer toId = Integer.valueOf(sc.next());
		Spaceship ship = spaceships.stream().filter(sh -> sh.getId().equals(shipId)).findAny().get();
		Planet from = planetMap.stream().filter(sh -> sh.getId().equals(fromId)).findAny().get();
		Planet to = planetMap.stream().filter(sh -> sh.getId().equals(toId)).findAny().get();
		MissionResult result = MissionResult.valueOf(sc.next());

		LocalDate startDate = LocalDate.ofEpochDay(ThreadLocalRandom.current()
				.longs(LocalDate.of(1990, 1, 1).toEpochDay(), LocalDate.now().toEpochDay()).findAny().getAsLong());
		LocalDate endDate = LocalDate.ofEpochDay(ThreadLocalRandom.current()
				.longs(startDate.toEpochDay(), LocalDate.now().toEpochDay()).findAny().getAsLong());
		Long distance = new Random().nextLong();

		missions.add(FlightMissionFactory.INSTANCE.create(name, startDate, endDate, distance, ship, from, to));

	}

}
