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

import java.util.function.Supplier;

/**
 * A Object representing the result of an operation which can go wrong.
 *
 * Here is an example of usage:
 *
 * <PRE>
 *
 *   Result result = Result.get( ()-> {
 *             try {
 *                 return new Result.Success(Integer.valueOf("Hi m8s"));
 *             } catch (NumberFormatException e) {
 *                 return new Result.Failure("That's not an int");
 *             }
 *         });
 *    if(result instanceof Result.Failure) {
 *        //Code to execute if the operation fails
 *    } else {
 *        //Code to execute if the operation is successful
 *         Integer integer = (Integer) ((Result.Success)result).getValue();
 *         System.out.print(integer.intValue());
 *    }
 *
 *
 * </PRE>
 *
 * @param <A> Value
 * @param <E> Error
 *
 * @author Diego Dominguez
 */
public class Result <A,E> {

    public static <A,E> Result<A,E> get(Supplier<Result<A,E>> mapper) {
        return mapper.get();
    }

    static class Success<A,E> extends Result {

        private  A value;

        public Success(A value){
            super();
            this.value = value;
        }

        public A getValue() {
            return value;
        }

    }
    static class Failure<A,E> extends Result {

        private  E error;

        public Failure(E error){
            super();
            this.error = error;
        }

        public E getError() {
            return error;
        }

    }
}
