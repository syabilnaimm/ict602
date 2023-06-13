package com.example.maltest;

public class NumberLimitException extends Exception {

    public NumberLimitException() {
        super();
    }

    public NumberLimitException(String name)
    {
        super(name);
    }
}