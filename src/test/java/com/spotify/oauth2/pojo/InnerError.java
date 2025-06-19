package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class InnerError {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("message")
	private String message;

	
	
}
