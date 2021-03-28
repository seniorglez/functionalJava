package com.seniorglez.functionalJava.fucntors;

import com.seniorglez.functionalJava.functors.Option;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestOption {
    @Test
    public void testOption() {
        Option<String> op = new Option<String>("3");
        int a = op.map((A)->Integer.parseInt(A));
        assertTrue(a == 3);
    }
}