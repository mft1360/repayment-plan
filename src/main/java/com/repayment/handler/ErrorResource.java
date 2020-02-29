package com.repayment.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author R.Fattahi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResource {

    @JsonInclude(Include.NON_NULL)
    private String field;

    @JsonInclude(Include.NON_NULL)
    private String message;
}
