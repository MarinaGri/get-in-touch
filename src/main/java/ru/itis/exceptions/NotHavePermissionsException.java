package ru.itis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotHavePermissionsException extends RuntimeException {

    public NotHavePermissionsException(String message) {
        super(message);
    }
}
