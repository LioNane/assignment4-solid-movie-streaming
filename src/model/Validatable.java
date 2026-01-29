package model;

import exception.InvalidInputException;

public interface Validatable<T> {

    abstract void validate();

    default T validateAndReturn(T value){
        validate();
        return value;
    }

    static void requireNonEmpty(String value, String fieldName){
        if(value == null || value.trim().isEmpty()){
            throw new InvalidInputException(fieldName + " must not be empty");
        }
    }
}
