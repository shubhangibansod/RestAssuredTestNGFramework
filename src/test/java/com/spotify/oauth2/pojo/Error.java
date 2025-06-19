package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Error {
	
	
	@JsonInclude(JsonInclude.Include.NON_NULL)

	@JsonProperty("error")
	private InnerError error;

	

}
