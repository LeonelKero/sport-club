package com.workbeattalent.sport_club.cycling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CyclingNotFoundException extends RuntimeException {
    public CyclingNotFoundException(String message) {
        super(message);
    }
}
