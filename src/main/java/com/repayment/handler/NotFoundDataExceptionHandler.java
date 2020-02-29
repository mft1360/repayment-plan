package com.repayment.handler;

import com.repayment.exception.NotFoundDataException;
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
 * During throwing a NotFoundDataException this handler can be to show a specific message for users.
 *
 * @author R.Fattahi
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NotFoundDataExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundDataExceptionHandler.class);

    @ExceptionHandler({NotFoundDataException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleInvalidRequest(NotFoundDataException exception, WebRequest request) {
        LOGGER.warn("data not found! {}", exception.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, ErrorResource.builder().message("data not found").build(), headers, HttpStatus.NOT_FOUND,
                request);
    }

}
