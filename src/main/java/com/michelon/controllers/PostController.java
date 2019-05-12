package com.michelon.controllers;

import com.michelon.dto.response.ResponseDto;

import spark.Request;

public interface PostController<T> {
	
	String getRoute();
	ResponseDto<?> handle(Request params, T body);
	Class<T> getBodyClass();
	
}

