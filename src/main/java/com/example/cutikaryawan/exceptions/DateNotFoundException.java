package com.example.cutikaryawan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DateNotFoundException {
	
	public DateNotFoundException(String message) {
		super();
		
	}
	
}
