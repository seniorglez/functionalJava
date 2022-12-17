/*Copyright (c) 2021 Diego Dominguez Gonzalez
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 *furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 */

package com.seniorglez.functionalJava.collections;

import com.seniorglez.functionalJava.monads.Option;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 *  This class consists exclusively of static methods that operate on or return collections.
 *
 * @author Diego Dominguez
 *
 */
public class CollectionsUtils {

    /**
     * Performs the given action all the elements of the given collection and returns a new one with the new elements.
     *
     * Keep in mind that this method internally uses the collection Iterator, so it is possible that the elements
     * of the new collection will come out out of order unless the collection that is passed as an argument implements
     * some type of rule that guarantees a certain order.
     *
     * @param collection The collection to be transformed.
     * @param mapper The action to be performed over all the collection values.
     * @return A new Collection with the new elements.
     */
    public static <F,T> Collection<T> map(Collection<F> collection, Function<? super F,T> mapper) {
        Iterator<F> iterator = collection.iterator();
        Class<? extends Collection> cls = collection.getClass();
        Collection result = instanceCollectionOf(cls);
        while (iterator.hasNext()) {
            Option<F> op = new Option<>(iterator.next());
            result.add(op.map(mapper));
        }
        return result;
    }

    /**
     * Reduces the collection to a single value.
     *
     * If any valor of the collection is null this method will return a void Option.
     *
     * @param collection The collection to be transformed.
     * @param mapper The action to be carried out on the first value of the collection and those that follow.
     * @return An Option which contains the resulting value.
     */
    public static <T> Option<T> flat(Collection<T> collection, BinaryOperator<T> mapper) {
        Iterator<T> iterator = collection.iterator();
        if (!iterator.hasNext()) return new Option<T>();
        T result = iterator.next();
        while (iterator.hasNext() && result!=null) {
            T aux = iterator.next();
            result =(aux==null)?  null : mapper.apply(result, aux);
        }
        return new Option<>(result);
    }

    /**
     * Returns a new Collection with values which meets the given condition.
     *
     * @param collection The collection to be filtered.
     * @param condition The condition to be evaluated.
     */
    public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> condition) {
        Iterator<T> iterator = collection.iterator();
        Class<? extends Collection> cls = collection.getClass();
        Collection result = instanceCollectionOf(cls);
        while (iterator.hasNext()) {
            Option<T> op = new Option<>(iterator.next());
            if(op.check(condition)) result.add(op.getValue());
        }
        return result;
    }

    /**
     * Returns a new Collection without the given Collection null values.
     *
     * @param collection The collection to be filtered.
     */
    public static <T> Collection<T> filterNotNull(Collection<T> collection) {
        return filter(collection,(A)-> A != null);
    }

    /**
     * Returns a new List with the same items of the given Collection.
     *
     * @param collection The Collection used to generate the new List.
     */
    public static <T> List<T> toList (Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        List<T> list = new LinkedList<>();
        while( iterator.hasNext() ) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * Returns an Option wrapped the index of the given item inside the given collection. The Option's value will be present if the item is
     * actually in the collection, otherwise the optional will be empty.
     * @param collection The collection to search in.
     * @param item The item to look for.
     */
    public static <T> Option<Integer> findIndex(Collection<T> collection, T item) {
        int index = toList(collection).indexOf(item);
        if( index >= 0 ) {
            return new Option<>(Integer.valueOf(index));
        }
        return new Option<>();
    }

    /**
     * Returns true it there is at least one item in the given collection witch matches the given condition.
     * @param collection The given collection.
     * @param condition The given collection.
     */
    public static <T> boolean anyMatch(Collection<T> collection, Predicate<T> condition) {
         if(isEmpty(collection)) {
             return false;
         }
         Collection<T> result = filter(collection, condition);
         return !isEmpty(result);
    }

    /**
     * Returns true if the given collection is not null and is not empty.
     *
     * @param collection the given collection.
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        if(isNull(collection) || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    //private methods

    private static Collection instanceCollectionOf(Class<? extends Collection> tClass) {
        try {
            Constructor<? extends Collection> constructor = tClass.getConstructor();
            return  constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

