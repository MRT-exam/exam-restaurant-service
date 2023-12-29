package com.mtgo.exam.restaurantservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorObject {
    private String message;
    private Integer statusCode;
    private Date timeStamp;
}
