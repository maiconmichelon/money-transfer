package com.michelon.dto.response;

public class ResponseDto<T> {

    private final T data;
    private final ErrorDto error;
    private final transient int statusCode;

    public ResponseDto(int statusCode, T data) {
        this.statusCode = statusCode;
		this.data = data;
        this.error = null;
    }

    public ResponseDto(int statusCode, ErrorDto error) {
        this.statusCode = statusCode;
		this.data = null;
        this.error = error;
    }
    
    public ResponseDto(int statusCode) {
    	this.statusCode = statusCode;
		this.data = null;
    	this.error = null;
    }

    public ErrorDto getError() {
        return error;
    }

    public T getData() {
        return data;
    }
    
    public int getStatusCode() {
		return statusCode;
	}

}
