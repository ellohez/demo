package com.qa.demo.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "no cat found with that ID")
public class CatNotFoundException extends RuntimeException {
    
    // Generate the serialisable ID

}
