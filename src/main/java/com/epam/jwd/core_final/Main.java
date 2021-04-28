package com.epam.jwd.core_final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws InvalidStateException, IOException {

		/*Properties pro = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Q:\\new-workspace\\jwd-core-final\\logger.properties");
		pro.load(is);
		File file = new File("resources.properties");
		System.out.println(file.getAbsolutePath());*/

		
		logger.info("printed user menu");

		
		Application.start().printAvailableOptions().handleUserInput();
		

	}
}