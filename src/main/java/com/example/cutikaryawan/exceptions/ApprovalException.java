package com.example.cutikaryawan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApprovalException extends RuntimeException {

	public ApprovalException(String message) {
		super(message);
	}
	
}
