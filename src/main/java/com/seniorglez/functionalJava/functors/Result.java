package com.seniorglez.functionalJava.functors;

public class Result <A,E> {

    static class Sucess<A,E> extends Result {

        private  A value;

        public Sucess(A value){
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
