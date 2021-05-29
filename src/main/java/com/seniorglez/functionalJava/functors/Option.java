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

package com.seniorglez.functionalJava.functors;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Wraps a value that can be null, thus avoiding {@link NullPointerException}. You can carry out all the transformations
 * you consider through the map function and then check if the value is null without fear of {@link NullPointerException}.
 * @author Diego Dominguez
 * @see NullPointerException
 */
public class Option<T>
{
    /**
     * Constructs a Option containing the value of the specified type.
     * @param value
     */
    public Option(T value){
        this.value = value;
}

    /**
     * Constructs an empty Option.
     */
    public Option(){
        value = null;
    }

    /**
     * The value wrapped by the Option.
     */
    private final T value;

    /**
     * Performs the given action to the Option value.
     * @param mapper The action to be performed over the value.
     * @param <U>
     * @return An Option that wraps the value resulting from the execution of the action on the current value.
     */
    public <U> Option<U> flatMap(Function<? super T, ? extends U> mapper) {
        return (this.value == null) ? new Option<U>() : new Option<U>(mapper.apply(value));
    }

    /**
     * Performs the given action to the Option value.
     * @param mapper The action to be performed over the value.
     * @param <U>
     * @return The value resulting from the execution of the action on the current value or null if its null.
     */
    public <U> U map(Function<? super T, ? extends U> mapper) {
        return this.flatMap(mapper).getValue();
    }

    /** Checks if the Option value meets the given condition
     * @param condition The condition to be evaluated
     * @return true if the value meets the given condition.
     */
    public boolean check(Predicate<? super T> condition) {
        return condition.test(value);
    }

    public T orElse(T other) {
        return value !=null? value : other;
    }

    /**
     * Returns the value wrapped by the Option.
     * @return The value
     */
    public T getValue() {
        return this.value;
    }
}