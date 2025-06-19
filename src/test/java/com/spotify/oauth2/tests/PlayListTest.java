package com.spotify.oauth2.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;
import com.spotify.oauth2.utils.FakersUtil;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.*;
import com.spotify.oauth2.pojo.*;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.utils.*;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

@Epic("Spotify OAuth2.0")
@Feature("Playlist API")
public  class PlayListTest extends BaseTest {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	
	@Story("Create a playlist story")
	@Link("https://example.com")
	@TmsLink("12345")
	@Issue("1212")
	@Description("this test case create a play list")
	@Attachment(value = "Page screenshot", type = "image/png")
	@Test(description = "Should be able to create play list")
	public void ShouldBeAbleToCreatePlayList() throws IOException {
		
		Playlist requestPlayList = PlayListBuilder(FakersUtil.generateName(),
				FakersUtil.generateDescription(), false);
		
		Response response = PlayListAPI.post(requestPlayList);
		assertStatusCode(response.statusCode(), StatusCode.CODE_201);
		assertPlayListEqual(response.as(Playlist.class),requestPlayList);
			
		
	}
	
	@Description("this test case retrive a play list")	
@Test(description = "Should be able to get play list")	
public void ShouldBeAbleToGetPlayList() throws IOException {
	
	Playlist requestPlayList = PlayListBuilder(" My New Spotify song Playlist",
			"My New spoify song playlist description", true);
		
		Response response = PlayListAPI.get(DataLoader.getInstance().getPlaylistID());
		assertStatusCode(response.statusCode(), StatusCode.CODE_200);
		
		assertPlayListEqual(response.as(Playlist.class),requestPlayList);
	
		
	}

	@Description("this test case update a play list")
@Test(description = "Should be able to update play list")
public void ShouldBeAbleToUpdatePlayList() throws IOException {
	
	Playlist requestPlayList = PlayListBuilder(FakersUtil.generateName(),
			FakersUtil.generateDescription(), false);
	
	Response response = PlayListAPI.update(requestPlayList, DataLoader.getInstance().Get_updated_playlist_id());
	
	assertStatusCode(response.statusCode(),StatusCode.CODE_200);
}
	
//negative scenario
	@Story("Create a playlist story")
	@Description("this test case create a play list without name")
@Test(description = "Should not be able to create playlist without name")
public void ShouldNotBeAbleToCreatePlayListWithoutName() throws IOException {
	
	Playlist requestPlayList = PlayListBuilder("",
			FakersUtil.generateDescription(), false);
	
	Response response = PlayListAPI.post(requestPlayList);
	assertStatusCode(response.statusCode(),StatusCode.CODE_400);

	assertError(response.as(Error.class),StatusCode.CODE_400);
	 
	
	
}
	@Link("https://example.com.org")
	@TmsLink("799")
	@Issue("1682")
	@Story("Create a playlist story")
	@Description("this test case belongs to negative scenario")
@Test(description = "Should not be able to create playlist with expired token")
public void ShouldNotBeAbleToCreatePlayListWithExpiredToken() throws IOException {
	
	Playlist requestPlayList = PlayListBuilder(FakersUtil.generateName(),
			FakersUtil.generateDescription(), false);
	String token ="Bearer 1234567";
		Response response = PlayListAPI.post(token,requestPlayList);
		assertStatusCode(response.statusCode(),StatusCode.CODE_401);
	assertError(response.as(Error.class),StatusCode.CODE_401);
	

}


@Step
public Playlist PlayListBuilder(String name,String description ,boolean _public) {
	//if we use builder as annotation in lombok playlist pojo 
	return Playlist.builder().name(name).description(description)._public(_public).build();
	//below code is without builder
	
	/*Playlist playlist  = new Playlist();
	playlist.setName(name);
	playlist.setDescription(description);
	playlist.set_public(_public);
	return playlist;*/
	
}

@Step
public void assertPlayListEqual(Playlist responsePlaylist, Playlist requestPlayList) {
	
	assertThat(responsePlaylist.getName(), equalTo(requestPlayList.getName()));
	assertThat(responsePlaylist.getDescription(), equalTo(requestPlayList.getDescription()));
	assertThat(responsePlaylist.get_public(), equalTo(requestPlayList.get_public()));
}

@Step
public void assertStatusCode(int actual_code,StatusCode expected_code) {
	
	assertThat(actual_code, equalTo(expected_code.code));
}

@Step
public void assertError(Error responseError,StatusCode statuscode) {
	
	 assertThat(responseError.getError().getStatus(),equalTo(statuscode.code));
	 assertThat(responseError.getError().getMessage(), equalTo(statuscode.msg));
	
}
}
