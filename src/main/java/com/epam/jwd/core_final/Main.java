package com.epam.jwd.core_final;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws InvalidStateException, IOException {

		logger.info("program started");

		Application.start().printAvailableOptions().handleUserInput();

		logger.info("program ended");

	}
}