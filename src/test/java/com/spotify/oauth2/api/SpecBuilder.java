package com.spotify.oauth2.api;

import com.spotify.oauth2.api.applicationApi.Route;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	

	
	public static RequestSpecification getRequestSpec() {
		return  new RequestSpecBuilder()
				.setBaseUri(System.getProperty("BASE_URI"))
				
				//.setBaseUri("https://api.spotify.com")
				.setBasePath(Route.BASE_PATH)
				.setContentType(ContentType.JSON)
				.addFilter(new AllureRestAssured())
				.log(LogDetail.ALL).build();
				
	}
	
	public static RequestSpecification getAccountRequestSpec() {
		return  new RequestSpecBuilder()
				.setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
				//.setBaseUri("https://accounts.spotify.com")
				.setContentType(ContentType.URLENC)
				.addFilter(new AllureRestAssured())
				.log(LogDetail.ALL).build();
				
	}
	
	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder()
				.log(LogDetail.ALL).build();
		}
			
	

}
