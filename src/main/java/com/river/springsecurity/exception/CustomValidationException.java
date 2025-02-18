package com.river.springsecurity.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // 422 status code
public class CustomValidationException extends RuntimeException {

    private String message;

}

