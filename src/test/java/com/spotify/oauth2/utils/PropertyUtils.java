package com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	
	public static Properties ProprtyLoader(String Filepath) throws IOException {
		Properties properties = new Properties();
		BufferedReader reader= new BufferedReader(new FileReader(Filepath));
		properties.load(reader);
		reader.close();
		return properties;
		
	}

}
