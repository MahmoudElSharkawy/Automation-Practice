package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class PropertiesReader {
    private static String propRoot = "src/main/resources/";

    public static void loadProperties() {
	Properties properties = new Properties();
	Collection<File> propertiesFilesList;
	propertiesFilesList = FileUtils.listFiles(new File(propRoot), new String[] { "properties" }, true);
	propertiesFilesList.forEach(propertyFile -> {
	    try {
		properties.load(new FileInputStream(propertyFile));
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	    } catch (IOException ioe) {
		System.out.println(ioe.getMessage());
	    }
	    properties.putAll(System.getProperties());
	    System.getProperties().putAll(properties);
	});
    }

}
