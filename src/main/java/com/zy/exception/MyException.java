package com.zy.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 自定义异常
 */
public class MyException extends Exception {
    public  MyException(String message){
        super(message);
    }
}
