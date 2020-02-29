package com.repayment.exception;


import lombok.Builder;

/**
 * if a query does not have a result you can throw this exception or there are not datas in data base or a method that returns null value.
 *
 * @author R.Fattahi
 */
public class NotFoundDataException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public NotFoundDataException() {

    }

    public NotFoundDataException(String message) {
        super(message);
    }


}
