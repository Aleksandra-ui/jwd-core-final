package com.epam.jwd.core_final.domain;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
	
	private String inputRootDir;
	private String outputRootDir; 
	private String crewFileName;
	private String missionsFileName;
	private String spaceshipsFileName; 
	private Integer fileRefreshRate;
	private String dateTimeFormat;
	
	static ApplicationProperties instance;
	
	private ApplicationProperties(String inputRootDir,String outputRootDir,String crewFileName,
			String missionsFileName, String spaceshipsFileName, Integer fileRefreshRate, String dateTimeFormat) {
	
		this.crewFileName = crewFileName;
		this.dateTimeFormat = dateTimeFormat;
		this.inputRootDir = inputRootDir;
		this.missionsFileName = missionsFileName;
		this.outputRootDir = outputRootDir;
		this.spaceshipsFileName = spaceshipsFileName;
		
	}
	
	public static ApplicationProperties newInstance(String inputRootDir,String outputRootDir,String crewFileName,
			String missionsFileName, String spaceshipsFileName, Integer fileRefreshRate, String dateTimeFormat) {
		if (instance == null){
			instance = new ApplicationProperties(inputRootDir, outputRootDir, crewFileName,
			missionsFileName, spaceshipsFileName, fileRefreshRate, dateTimeFormat);
		} 
		return instance;
	}
	
	public static ApplicationProperties getInstance() {
		return instance;
	}
	
	public String getInputRootDir() {
		return inputRootDir;
	}
	
	public String getOutputRootDir() {
		return outputRootDir;
	}
	
	public String getCrewFileName() {
		return crewFileName;
	}
	
	public String getMissionsFileName() {
		return missionsFileName;
	}
	
	public String getSpaceshipsFileName() {
		return spaceshipsFileName;
	}
	
	public Integer getFileRefreshRate() {
		return fileRefreshRate;
	}
	
	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	
}
