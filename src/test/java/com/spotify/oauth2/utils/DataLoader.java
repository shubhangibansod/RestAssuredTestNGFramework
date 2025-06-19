package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class DataLoader {
	
	private final Properties properties;
	private static DataLoader dataLoader;
	
	private DataLoader() throws IOException {
		properties = PropertyUtils.ProprtyLoader("src/main/resources/data.properties");
		
	}
	
	public static  DataLoader getInstance() throws IOException {
		if(dataLoader==null) {
			dataLoader=   new DataLoader();
		}
		return dataLoader;
	}

	public String getPlaylistID() {
		String prop = properties.getProperty("get_playlist_id");
		if(prop!=null) {
			return prop;
		}
		else
		{
			throw new RuntimeException("Property get_playlist_id is not specified in config.peroperties file");
		}
	}
	
	public String Get_updated_playlist_id() {
		String prop = properties.getProperty("updated_playlist_id");
		if(prop!=null) {
			return prop;
		}
		else
		{
			throw new RuntimeException("Property updated_playlist_id is not specified in config.peroperties file");
		}
	}	
}
