package com.seniorglez.functionalJava.monads;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestOption {
    @Test
    public void testFlatMapTheValueIsNotNull() {
        Option<String> op = new Option<>("3");
        int a = op.flatMap((A)->Integer.parseInt(A)).getValue();
        assertTrue(a == 3);
    }

    @Test
    public void testFlatMapTheValueIsNull() {
        Option<String> op = new Option<>(null);
        Integer a = op.flatMap((A)->Integer.valueOf(A)).getValue();
        assertTrue(a == null);
    }

    @Test
    public void testMapNotNull() {
        Option<String> op = new Option<>("3");
        int a = op.map((A)->Integer.parseInt(A));
        assertTrue(a == 3);
    }

    @Test
    public void testMapNull() {
        Option<String> op = new Option<>(null);
        Integer a = op.map((A)->Integer.valueOf(A));
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

    @Test
    public void testIsPresentShouldReturnFalse() {
        Option<String> op = new Option<>();
        assertTrue(!op.isPresent());
    }

    @Test
    public void testIsPresentShouldReturnTrue() {
        Option<String> op = new Option<>("Aitona");
        assertTrue(op.isPresent());
    }


}