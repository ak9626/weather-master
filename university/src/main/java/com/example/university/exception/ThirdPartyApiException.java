package com.example.university.exception;

/**
 * ClassName: ThirdPartyApiException
 * Package: com.example.university.exception
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 19:58
 * @version 1.0
 */
public class ThirdPartyApiException extends RuntimeException {
    public ThirdPartyApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThirdPartyApiException(String message) {
        super(message);
    }
}
