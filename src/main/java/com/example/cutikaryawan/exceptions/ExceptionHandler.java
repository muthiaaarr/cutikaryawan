package com.example.cutikaryawan.exceptions;

import java.util.Date;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

/*//	@ExceptionHandler(value = {RequestLeaveException.class})
//	public ResponseEntity<Object> handleRequestLeaveException(RequestLeaveException ex, WebRequest request) {
//		
//		String errorMessageDescription = ex.getLocalizedMessage();
//		if (errorMessageDescription == null) errorMessageDescription = ex.toString();
//		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
//		
//		return new ResponseEntity<>(
//				errorMessage, new HttpHeaders(), HttpStatus.NO_CONTENT);
//	}
*/	
	
}
