package com.repayment.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * to save and calculate repayment plan.
 *
 * @author R.Fattahi
 */
@Data
@Builder
public class RePaymentPlan {

    private double borrowerPaymentAmount;

    private Date date;

    private double initialOutstandingPrincipal;

    private double interest;

    private double principal;

    private double remainingOutstandingPrincipal;

}
