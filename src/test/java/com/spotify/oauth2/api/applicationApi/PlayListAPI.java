package com.spotify.oauth2.api.applicationApi;

import java.io.IOException;

import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlayListAPI {

	
	@Step
	public static Response post(Playlist requestPlayList) throws IOException {
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().get_Users()
				+Route.PLAYLISTS, TokenManager.getToken(), requestPlayList);
		}
@Step	
public static Response post(String token,Playlist requestPlayList) throws IOException {
	return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().get_Users()
			+Route.PLAYLISTS, token, requestPlayList);
		}
	
	public static Response get(String playlistId) {
		return RestResource.get(Route.PLAYLISTS+"/"+playlistId,TokenManager.getToken());
		
	}
	
	public static Response update(Playlist requestPlayList,String playlistId) {
		return RestResource.update(Route.PLAYLISTS+"/"+playlistId, TokenManager.getToken(), requestPlayList);
		
	}
		
	
}
