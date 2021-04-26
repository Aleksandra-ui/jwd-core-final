package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// todo
public class NassaContext implements ApplicationContext {

	// no getters/setters for them
	private Collection<CrewMember> crewMembers = new ArrayList<>();
	private Collection<Spaceship> spaceships = new ArrayList<>();
	private Collection<Planet> planetMap = new ArrayList<>();
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
	public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
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
		System.out.println(spaceships);

	}

	private void readCrew(String inputDir) {

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
				crewMembers.add(new CrewMember(i++, Role.resolveRoleById(Integer.valueOf(sc.next())), sc.next(),
						Rank.resolveRankById(Integer.valueOf(sc.next()))));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*private void readShips(String inputDir) {

		String shipsFileName = ApplicationProperties.getInstance().getSpaceshipsFileName();
		long id = 1;
		String name;
		Scanner word;
		Spaceship spaceship;

		try {
			String ships = new String(Files.readAllBytes(Paths.get(inputDir + "/" + shipsFileName)),
					StandardCharsets.UTF_8);
			Scanner line = new Scanner(ships);
			line.nextLine();
			line.nextLine();
			line.nextLine();
			//System.out.println(line.nextLine());
			String ship = line.nextLine();
			
			while ( line.hasNextLine() ) {
				word = new Scanner(ship);
				word.useDelimiter(";");
				name = word.next();
				Long dist = word.nextLong();
				String crew = word.next();
				crew = crew.substring(1, crew.length()-1);
				String[] members = crew.split(",");
				Map<Role,Short> crew2= new HashMap<>();
				for (String member : members) {
					crew2.put(Role.resolveRoleById(Integer.valueOf(member.substring(0, 1))), Short.valueOf(member.substring(2, 3)));
				spaceship=new Spaceship( id++, name, crew2, dist);
				spaceships.add(spaceship);
				if (line.hasNextLine()) {
					ship = line.nextLine();
					System.out.println(ship);
				}
			}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/
	
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
			//System.out.println(line.nextLine());
			String ship = line.nextLine();
			
			while ( line.hasNextLine() ) {
				String[] charasteristics = ship.split(";");
				name = charasteristics[0];
				distance = Long.valueOf(charasteristics[1]);
				members = charasteristics[2].substring(1, charasteristics[2].length() - 1).split(",");
				crew = new HashMap<>();
				for ( String member: members ) {
					crew.put(Role.resolveRoleById(Integer.valueOf(member.substring(0, 1))), Short.valueOf(member.substring(2, 3)) );
				}
				spaceship = new Spaceship(id++, name, crew, distance);
				spaceships.add(spaceship);
				
				ship = line.nextLine();
				System.out.println(ship);
			}
			String[] charasteristics = ship.split(";");
			name = charasteristics[0];
			distance = Long.valueOf(charasteristics[1]);
			members = charasteristics[2].substring(1, charasteristics[2].length() - 1).split(",");
			crew = new HashMap<>();
			for ( String member: members ) {
				crew.put(Role.resolveRoleById(Integer.valueOf(member.substring(0, 1))), Short.valueOf(member.substring(2, 3)) );
			}
			spaceship = new Spaceship(id++, name, crew, distance);
			spaceships.add(spaceship);


		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
