package com.repayment.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author R.Fattahi
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DuringExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DuringExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> handleInvalidRequest(Exception exception, WebRequest request) {
		LOGGER.warn("there is an exception {}", exception.getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(exception, ErrorResource.builder().message("there is an exception"), headers,
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
