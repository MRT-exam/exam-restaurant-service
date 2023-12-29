package com.mtgo.exam.restaurantservice.exception.handler;

import com.mtgo.exam.restaurantservice.exception.ErrorObject;
import com.mtgo.exam.restaurantservice.exception.ErrorMessages;
import com.mtgo.exam.restaurantservice.exception.error.RestaurantNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorObject> handleRestaurantNotFoundException(RestaurantNotFoundException ex) {
        log.error(ex.getLocalizedMessage(), ex);

        return buildErrorResponse(
                Objects.nonNull(ex.getLocalizedMessage()) ? ex.getLocalizedMessage() : ErrorMessages.RESTAURANT_NOT_FOUND_ERROR,
                HttpStatus.NOT_FOUND,
                new Date());
    }


    private ResponseEntity<ErrorObject> buildErrorResponse(String message, HttpStatus status, Date date) {
        return ResponseEntity.status(status).body(new ErrorObject(message, status.value(), date));
    }
}
