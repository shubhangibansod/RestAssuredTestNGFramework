package com.spotify.oauth2.api.applicationApi;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class TokenManager {
	private static String access_token;
	private static  Instant expiry_time;
	
	public synchronized static String getToken() {
		
		try {
			if(access_token == null || Instant.now().isAfter(expiry_time)) {
			
				System.out.println("renewing token.............");
				System.out.println("current time :"+Instant.now());
				Response res = renew_token();
				access_token = res.path("access_token");
				int expiryDurationInSeconds = res.path("expires_in");
				expiry_time = Instant.now().plusMillis(expiryDurationInSeconds-300);
				System.out.println("expiry time :"+Instant.now().isAfter(expiry_time));
			}
			else
			{
				System.out.println("Token is good to use.....");
			}
		}
		catch(Exception E){
			throw new RuntimeException("ABORT!! failed to get new token");
		}
		
	 
	 
	 return access_token;
	}

	private static Response renew_token() throws IOException {
		
		HashMap<String, String> formParam = new HashMap<String, String>();
		formParam.put("grant_type", ConfigLoader.getInstance().get_Grant_type());
		formParam.put("refresh_token", ConfigLoader.getInstance().Get_Refresh_Token());
		formParam.put("client_id", ConfigLoader.getInstance().getClientID());
		formParam.put("client_secret", ConfigLoader.getInstance().get_Client_Secret());
		
		Response response = RestResource.postAccount(formParam);
		
		if(response.statusCode()!=200) {
			throw new RuntimeException("ABORT!!Renew tokeen failed");
		}
		
		return response;
		
	}
}
