package com.repayment.java8;

import java.util.StringJoiner;

/**
 * @author m.fatthi
 */
public class FunctionalInterfaceExample implements Sayable {
    @Override
    public void say(String msg) {

        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add("Rahul");
        stringJoiner.add("Raju");
        stringJoiner.add("Peter");
        stringJoiner.add("Raheem");
        System.out.println(stringJoiner);
        //Rahul,Raju,Peter,Raheem
    }
}
