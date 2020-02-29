package com.repayment.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * to save and calculate repayment plan.
 *
 * @author R.Fattahi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private Double loanAmount;

    private double nominalRate;

    private Integer duration;

    private Date startDate;
}
