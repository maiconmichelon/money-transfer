package com.michelon.controllers;

import com.michelon.dto.response.ResponseDto;

import spark.Request;

public interface Controller {

	String getRoute();
	ResponseDto<?> handle(Request request);
	
}
