package com.seniorglez.functionalJava.functors;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestOption {
    @Test
    public void testTheValueIsNotNull() {
        Option<String> op = new Option<>("3");
        int a = op.map((A)->Integer.parseInt(A)).getValue();
        assertTrue(a == 3);
    }

    @Test
    public void testTheValueIsNull() {
        Option<String> op = new Option<>(null);
        Integer a = op.map((A)->Integer.valueOf(A)).getValue();
        assertTrue(a == null);
    }

    @Test
    public void testCheck() {
        Option<String> op = new Option<>("3");
        boolean a = op.check((A)->A == "3");
        assertTrue(a);
    }

    @Test
    public void testCheckFail() {
        Option<String> op = new Option<>("3");
        boolean a = op.check((A)->A == "I think its a joke name, Sir. Like Sillius Soddus or Biggus Dickus");
        assertTrue(!a);
    }
}