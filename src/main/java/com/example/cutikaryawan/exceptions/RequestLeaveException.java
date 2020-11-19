package com.example.cutikaryawan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class RequestLeaveException extends RuntimeException {

	public RequestLeaveException(String message) {
		super(message);
	}
	
}
