package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	
	private final Properties properties;
	private static ConfigLoader configLoader;
	
	private ConfigLoader() throws IOException {
		properties = PropertyUtils.ProprtyLoader("src/main/resources/config.properties");
		
	}
	
	public static  ConfigLoader getInstance() throws IOException {
		if(configLoader==null) {
			configLoader=   new ConfigLoader();
		}
		return configLoader;
	}

	public String getClientID() {
		String prop = properties.getProperty("client_id");
		if(prop!=null) {
			return prop;
		}
		else
		{
			throw new RuntimeException("Property client_id is not specified in config.peroperties file");
		}
	}
	
	public String get_Client_Secret() {
		String prop = properties.getProperty("client_secret");
		if(prop!=null) {
			return prop;
		}
		else
		{
			throw new RuntimeException("Property client_secret is not specified in config.peroperties file");
		}
	}
		
		public String get_Grant_type() {
			String prop = properties.getProperty("grant_type");
			if(prop!=null) {
				return prop;
			}
			else
			{
				throw new RuntimeException("Property grant_type is not specified in config.peroperties file");
			}
		}
		
		public String get_Users() {
			String prop = properties.getProperty("user_id");
			if(prop!=null) {
				return prop;
			}
			else
			{
				throw new RuntimeException("Property users is not specified in config.peroperties file");
			}
		}
		
		public String Get_Refresh_Token() {
			String prop = properties.getProperty("refresh_token");
			if(prop!=null) {
				return prop;
			}
			else
			{
				throw new RuntimeException("Property refresh_tokn is not specified in config.peroperties file");
			}
		}
}
