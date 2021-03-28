package com.seniorglez.functionalJava.functors;

import java.util.function.Function;

public class Option<T>
{
    public Option(T value){
        this.value = value;
}
    private T value;

    public <U> U map(Function<? super T, ? extends U> mapper) {
        return (this == null) ? null : mapper.apply(value);
    }

    public T getValue(T value) {
        return this.value;
    }
}