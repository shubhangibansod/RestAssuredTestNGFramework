package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakersUtil {
	
	public static String generateName() {
		Faker faker = new Faker();
		return "PlaylistName:"+ faker.regexify("[A-Za-z0-9,_-]{10}");
	}
	
	public static String generateDescription() {
		Faker faker = new Faker();
		return "PlaylistDescription:"+ faker.regexify("[A-Za-z0-9_@./#&+-]{50}");
	}

}
