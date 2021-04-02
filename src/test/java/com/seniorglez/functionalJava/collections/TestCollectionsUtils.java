package com.seniorglez.functionalJava.collections;

import com.seniorglez.functionalJava.functors.Option;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

public class TestCollectionsUtils {

    @Test
    public void testMapSmoke() {
        LinkedList<String> ll = new LinkedList<String>();
        ll.add("1");
        ll.add("2");
        ll.add("3");
        CollectionsUtils.map(ll,(A)->Integer.parseInt(A));
    }

    @Test
    public void testMapSmokeNulls() {
        LinkedList<String> ll = new LinkedList<String>();
        ll.add("1");
        ll.add("2");
        ll.add(null);
        CollectionsUtils.map(ll,(A)->Integer.parseInt(A));
    }

    @Test
    public void testMapOneElement() {
        LinkedList<String> ll = new LinkedList<String>();
        ll.add("1");
        Collection<Integer> result = CollectionsUtils.map(ll,(A)->Integer.parseInt(A));
        assertTrue(result.contains(Integer.valueOf(1)));
    }

    @Test
    public void testFlatInteger() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(Integer.valueOf(1));
        ll.add(Integer.valueOf(2));
        ll.add(Integer.valueOf(3));
        ll.add(Integer.valueOf(4));
        ll.add(Integer.valueOf(5));
        ll.add(Integer.valueOf(6));
        ll.add(Integer.valueOf(7));
        ll.add(Integer.valueOf(8));
        ll.add(Integer.valueOf(9));
        ll.add(Integer.valueOf(10));
        Option<Integer> result = CollectionsUtils.flat(ll,(x1, x2) -> x1 + x2);
        assertTrue(result.getValue() == 55);
    }

    @Test
    public void testFlatString() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Three");
        ll.add("Rings");
        ll.add("for");
        ll.add("the");
        ll.add("Elven-kings");
        ll.add("under");
        ll.add("the");
        ll.add("sky");
        Option<String> result = CollectionsUtils.flat(ll,(x1, x2) -> x1 + " " + x2);
        assertTrue(result.getValue().equals("Three Rings for the Elven-kings under the sky"));
    }

    @Test
    public void testFlatNullShouldReturnNull() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Three");
        ll.add(null);
        ll.add("for");
        ll.add("the");
        ll.add("Elven-kings");
        ll.add("under");
        ll.add("the");
        ll.add("sky");
        Option<String> result = CollectionsUtils.flat(ll,(x1, x2) -> x1 + " " + x2);
        assertTrue(result.getValue()==null);
    }

    @Test
    public void testFlatSingleValue() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Three Rings for the Elven-kings under the sky");
        Option<String> result = CollectionsUtils.flat(ll,(x1, x2) -> x1 + " " + x2);
        assertTrue(result.getValue().equals("Three Rings for the Elven-kings under the sky"));
    }

    @Test
    public void testFlatEmptyCollection() {
        LinkedList<String> ll = new LinkedList<>();
        Option<String> result = CollectionsUtils.flat(ll,(x1, x2) -> x1 + " " + x2);
        assertTrue(result.getValue()==null);
    }

    @Test
    public void testFilterString() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("pepe");
        ll.add("pepe");
        ll.add("p");
        ll.add("asdasd");
        LinkedList<String> result = (LinkedList<String>) CollectionsUtils.filter(ll,(A)->A.equalsIgnoreCase("pepe"));
        assertTrue(result.size() == 2);
    }

    @Test
    public void testFilterInteger() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(Integer.valueOf(1));
        ll.add(Integer.valueOf(2));
        ll.add(Integer.valueOf(3));
        ll.add(Integer.valueOf(4));
        ll.add(Integer.valueOf(5));
        ll.add(Integer.valueOf(6));
        ll.add(Integer.valueOf(7));
        ll.add(Integer.valueOf(8));
        ll.add(Integer.valueOf(9));
        ll.add(Integer.valueOf(10));
        LinkedList<Integer> result = (LinkedList<Integer>) CollectionsUtils.filter(ll,(A)->A.intValue()>4);
        assertTrue(result.size() == 6);
    }

    @Test
    public void testFilterNotNull() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(null);
        ll.add(null);
        ll.add(null);
        ll.add(null);
        ll.add(null);
        ll.add(null);
        ll.add(null);
        ll.add(Integer.valueOf(1));
        ll.add(Integer.valueOf(2));
        ll.add(Integer.valueOf(3));
        ll.add(Integer.valueOf(4));
        ll.add(null);
        ll.add(Integer.valueOf(5));
        ll.add(Integer.valueOf(6));
        ll.add(null);
        ll.add(Integer.valueOf(7));
        ll.add(Integer.valueOf(8));
        ll.add(Integer.valueOf(9));
        ll.add(null);
        ll.add(Integer.valueOf(10));
        ll.add(null);
        ll.add(null);
        ll.add(null);
        ll.add(null);
        LinkedList<Integer> result = (LinkedList<Integer>) CollectionsUtils.filterNotNull(ll);
        assertTrue(result.size() == 10);
        result.forEach(A -> assertTrue(A != null));
    }
}
