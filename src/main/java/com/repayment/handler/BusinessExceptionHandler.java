package com.repayment.handler;

import com.repayment.exception.BusinessException;
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
 * for handing businessException that is threw by programmer.
 * @author R.Fattahi
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidRequest(BusinessException exception, WebRequest request) {
        LOGGER.warn("there is an exception {}", exception.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, ErrorResource.builder().message(exception.getMessage()), headers,
                HttpStatus.BAD_REQUEST, request);
    }

}
