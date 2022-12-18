package com.seniorglez.functionalJava.collections;

import com.seniorglez.functionalJava.monads.Option;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.seniorglez.functionalJava.collections.CollectionsUtils.anyMatch;
import static com.seniorglez.functionalJava.collections.CollectionsUtils.concat;
import static com.seniorglez.functionalJava.collections.CollectionsUtils.findIndex;
import static com.seniorglez.functionalJava.collections.CollectionsUtils.first;
import static com.seniorglez.functionalJava.collections.CollectionsUtils.isEmpty;
import static com.seniorglez.functionalJava.collections.CollectionsUtils.last;
import static org.junit.Assert.assertTrue;

public class TestCollectionsUtils {

    @Test
    public void testMapSmoke() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("1");
        ll.add("2");
        ll.add("3");
        CollectionsUtils.map(ll,(A)->Integer.parseInt(A));
    }

    @Test
    public void testMapSmokeNulls() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("1");
        ll.add("2");
        ll.add(null);
        CollectionsUtils.map(ll,(A)->Integer.parseInt(A));
    }

    @Test
    public void testMapOneElement() {
        LinkedList<String> ll = new LinkedList<>();
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

    @Test
    public void testFindIndexShouldReturnOptionNOTPresent() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Pepe");
        ll.add("Juan");
        ll.add("Carlos");
        ll.add("Jon Ander");
        Option<Integer> option = findIndex(ll,"Alma");
        assertTrue( !option.isPresent() );
    }

    @Test
    public void testFindIndexShouldReturnOptionISPresent() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Pepe");
        ll.add("Juan");
        ll.add("Carlos");
        ll.add("Jon Ander");
        Option<Integer> option = findIndex(ll,"Carlos");
        assertTrue( option.isPresent() );
    }

    @Test
    public void testIsEmptyShouldReturnTrueWhenTheCollectionIsNull() {
        LinkedList<String> ll = null;
        assertTrue(isEmpty(ll));
    }

    @Test
    public void testIsEmptyShouldReturnTrueWhenTheCollectionHasNoItems() {
        LinkedList<String> ll = new LinkedList<>();
        assertTrue(isEmpty(ll));
    }

    @Test
    public void testIsEmptyShouldReturnFalseIfTheCollectionHasItems() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Atocha");
        assertTrue(!isEmpty(ll));
    }

    @Test
    public void testAnyMatchShouldReturnFalseIfThereIsNoMatch() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Atocha");
        assertTrue(!anyMatch(ll, a -> a.length() > 100000000));
    }

    @Test
    public void testAnyMatchShouldReturnTrueIfThereIsAlLeastOneMatch() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Atocha");
        assertTrue(anyMatch(ll, a -> a.length() > 0));
    }

    @Test
    public void testAnyMarchShouldReturnFalseIfTheCollectionIsNull() {
        LinkedList<String> ll = null;
        assertTrue(!anyMatch(ll, a -> a.length() > 0));
    }

    @Test
    public void testConcatShouldReturnTheFirstItemOfTheFirstList() {
        LinkedList<String> lla = new LinkedList<>();
        lla.add("Gorri");
        lla.add("Beltz");
        LinkedList<String> llb = new LinkedList<>();
        llb.add("Hori");
        llb.add("Urdin");
        List<String> llc = concat(lla,llb);
        assertTrue(llc.get(0) == "Gorri");
    }

    @Test
    public void testConcatShouldReturnTheLastItemOfTheSecondList() {
        LinkedList<String> lla = new LinkedList<>();
        lla.add("Gorri");
        lla.add("Beltz");
        LinkedList<String> llb = new LinkedList<>();
        llb.add("Hori");
        llb.add("Urdin");
        List<String> llc = concat(lla,llb);
        assertTrue(llc.get(3) == "Urdin");
    }

    @Test
    public void testConcatShouldHaveTheSumOfBothCollectionSizes() {
        Collection<String> a = new LinkedList<>();
        a.add("Gorri");
        a.add("Beltz");
        Collection<String> b = new LinkedList<>();
        b.add("Hori");
        b.add("Urdin");
        Collection<String> c = concat(a,b);
        assertTrue(c.size() == 4);
    }

    @Test
    public void testFirst() {
        Collection<String> a = new LinkedList<>();
        a.add("Gorri");
        a.add("Beltz");
        a.add("Hori");
        a.add("Urdin");
        assertTrue(first(a) == "Gorri");
    }

    @Test
    public void testLast() {
        Collection<String> a = new LinkedList<>();
        a.add("Gorri");
        a.add("Beltz");
        a.add("Hori");
        a.add("Urdin");
        assertTrue(last(a) == "Urdin");
    }

}
