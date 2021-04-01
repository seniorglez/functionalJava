package com.seniorglez.functionalJava.functors;

/**
 * A Object representing the result of an operation which can go wrong.
 *
 * Here is an example of usage:
 *
 * <PRE>
 *
 *       public Result<Integer,String> mustBeAnInteger(String str) {
 *         try {
 *             return new Result.Success(Integer.valueOf(str));
 *         } catch (NumberFormatException e) {
 *             return new Result.Failure("That's not an int");
 *         }
 *     }
 *
 * </PRE>
 *
 * @param <A> Value
 * @param <E> Error
 *
 * @author Diego Dominguez
 */
public class Result <A,E> {

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
