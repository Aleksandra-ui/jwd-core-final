package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

	private static final Properties properties = new Properties();

	private PropertyReaderUtil() {
	}

	/**
	 * try-with-resource using FileInputStream
	 *
	 * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html
	 *      for an example}
	 *      <p>
	 *      as a result - you should populate {@link ApplicationProperties} with
	 *      corresponding values from property file
	 */
	public static void loadProperties() {
		final String propertiesFileName = "src/main/resources/application.properties";

		try (InputStream iStream = new FileInputStream(propertiesFileName)) {
			properties.load(iStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String inputRootDir=properties.getProperty("inputRootDir");
		String outputRootDir=properties.getProperty("outputRootDir");
		String crewFileName=properties.getProperty("crewFileName");
		String missionsFileName=properties.getProperty("missionsFileName");
		String spaceshipsFileName=properties.getProperty("spaceshipsFileName");
		Integer fileRefreshRate=Integer.valueOf(properties.getProperty("fileRefreshRate"));
		String dateTimeFormat=properties.getProperty("dateTimeFormat");
		String spacemapFileName=properties.getProperty("spacemapFileName");

		
		ApplicationProperties.newInstance(inputRootDir,outputRootDir,crewFileName,missionsFileName,
				spaceshipsFileName,fileRefreshRate,dateTimeFormat,spacemapFileName);

	}
}
