package com.repayment.service;

import com.repayment.entities.Loan;
import com.repayment.entities.RePaymentPlan;
import com.repayment.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * some method for calculate repayment plan that they can be overridden by the class.
 *
 * @author R.Fattahi
 */
public abstract class CalculatorRepayment {

    protected final int ONE_HUNDRED = 100;
    protected final int TWELVE = 12;
    protected final int THIRTY = 30;
    protected final int ONE = 1;
    protected final int NEGATIVE_ONE = -1;
    protected final int DAYS_IN_YEAR = 360;

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorRepayment.class);

    protected abstract List<RePaymentPlan> generatePlan(Loan loan);

    /*
     * This method rounds up to two decimal places
     *
     * @see com.repayment.service.CalculatorRepayment#round(double)
     */
    protected double round(double number) {
        return (double) Math.round(number * ONE_HUNDRED) / ONE_HUNDRED;
    }

    /*
     * This method calculates interested (Nominal-Rate * Days in Month * Initial
     * Outstanding Principall) / days in year
     *
     * @see com.repayment.service.CalculatorRepayment#calcInterested(double,
     * double)
     */
    protected double calcInterested(double rate, double initialOutstandingPrincipal) {
        LOGGER.debug("start {} with rate {} and initialOutstandingPrincipal {}", "calcInterested", rate, initialOutstandingPrincipal);
        if (rate == 0 || initialOutstandingPrincipal == 0) {
            throw new BusinessException("rate or initialOutstandingPrincipal can not be 0");
        }
        double interested = round(((double) rate / ONE_HUNDRED) * THIRTY * initialOutstandingPrincipal / DAYS_IN_YEAR);
        LOGGER.debug("executed successfully {} with rate {} and initialOutstandingPrincipal {}", "calcInterested", rate, initialOutstandingPrincipal);
        return interested;
    }

    /*
     * Accroding the document on
     * https://financeformulas.net/Annuity_Payment_Formula.html this method
     * calculates Annuity Payment
     *
     * @see com.repayment.service.CalculatorRepayment#calcAnnuity(double, double,
     * int)
     */

    protected double calcAnnuity(double initialOutstandingPrincipal, double rate, int duration) {
        LOGGER.debug("start {} with initialOutstandingPrincipal {} and rate {} and duration {}", "calcAnnuity", rate, duration);
        if (initialOutstandingPrincipal == 0 || rate == 0 || duration == 0) {
            throw new BusinessException("interest or ratePeriod or duration can not be 0");
        }
        double ratePeriod = (double) rate / ONE_HUNDRED / TWELVE;
        double annuity = round((initialOutstandingPrincipal * ratePeriod) / (ONE - (double) Math.pow((ONE + ratePeriod), (duration * NEGATIVE_ONE))));
        LOGGER.debug("executed successfully {} with initialOutstandingPrincipal {} and rate {} and duration {}", "calcAnnuity", rate, duration);
        return annuity;
    }

    /*
     * This method adds 30 days to input date
     *
     * @see com.repayment.service.CalculatorRepayment#nextMonth(java.util.Date)
     */
    protected Date nextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();

    }
}
