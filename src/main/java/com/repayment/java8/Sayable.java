package com.repayment.java8;

/**
 * @author m.fatthi
 */
@FunctionalInterface
public interface Sayable {

    void say(String msg);

    default void say1() {
        System.out.println("Hello, this is default method");
    }

    default void say2() {

    }

}
