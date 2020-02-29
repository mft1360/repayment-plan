package com.repayment.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author m.fatthi
 */
public class MethodReference {

    public MethodReference() {
        System.out.println("constructor");
    }

    public MethodReference(String msg) {
        System.out.println("constructor");
    }

    public static void saySomething(String msg) {
        System.out.println("Hello, this is static method.");
        List<String> strings = new ArrayList<>();
        strings.add("mohsen");
        strings.add("fattahi");
        strings.add("ereski");
        strings.forEach((item -> {
            String s = item;
            System.out.println(s);
        }));

    }

    public void saySomething1(String msg) {
        System.out.println("Hello, this is static method.");

    }

    public static void main(String[] args) {

        Sayable sayable = MethodReference::saySomething;
        sayable.say("Mohsen");//Reference to a static method.

        MethodReference methodReference = new MethodReference();
        sayable = methodReference::saySomething1;
        sayable.say("Fattahi");//Reference to an instance method.

        sayable = MethodReference::new;
        sayable.say("Ereski");//Reference to a constructor.

    }

}