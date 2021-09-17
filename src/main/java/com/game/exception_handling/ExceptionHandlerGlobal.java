package com.game.exception_handling;

import com.game.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerGlobal {

    @ExceptionHandler
    public ResponseEntity<Player> badIdRequest(Exception exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
