package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import base.baseTest;

public class PropertiesFile {

	public static Properties properties;

	public static void getProperties() {
		properties = new Properties();
		String projectPath = System.getProperty("user.dir");
		try (FileInputStream inputStream = new FileInputStream(
				projectPath + "/src/main/java/config/config.properties")) {
			// Load the properties from the input stream
			properties.load(inputStream);
			String browser = properties.getProperty("browser");
			baseTest.browserName = browser;
			String base_url = properties.getProperty("base_url");
			baseTest.baseURL = base_url;
			System.out.println("INFO: Successfully loaded properties from: " + projectPath);
		} catch (IOException e) {
			// Handle the exception if the file is not found or cannot be read
			System.err.println("FATAL: Could not read configuration file: " + projectPath);
			e.printStackTrace();
			throw new RuntimeException("Failed to load configuration file.", e);
		}
	}
}
