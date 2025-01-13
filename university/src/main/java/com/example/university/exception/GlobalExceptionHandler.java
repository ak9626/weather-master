package com.example.university.exception;

import com.example.common.domain.GeneralResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.example.university.exception
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 19:58
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ThirdPartyApiException.class)
    public GeneralResponse handleThirdPartyApiException(ThirdPartyApiException ex) {
        GeneralResponse response = new GeneralResponse();
        response.setCode(500);
        response.setTimestamp(new Date());
        response.setData("Error: " + ex.getMessage());
        return response;
    }
}
