package com.seniorglez.functionalJava.functors;

import java.util.function.Function;

public class Option<T>
{
    public Option(T value){
        this.value = value;
}
    private T value;

    public <U> Option<U> map(Function<? super T, ? extends U> mapper) {
        return (this.value == null) ? new Option<U>(null) : new Option<U>(mapper.apply(value));
    }

    public T getValue() {
        return this.value;
    }
}