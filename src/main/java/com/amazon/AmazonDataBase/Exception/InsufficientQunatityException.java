package com.amazon.AmazonDataBase.Exception;

public class InsufficientQunatityException extends  Exception{
    InsufficientQunatityException(String errorMessage){
        super(errorMessage);
    }
}
