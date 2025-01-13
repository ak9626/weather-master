package com.example.search;

import java.time.LocalDateTime;

public class GeneralResponse {

    private String code;
    private LocalDateTime timestamp;
    private String data;

    // Constructor
    public GeneralResponse(String code, LocalDateTime timestamp, String data) {
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // toString method (optional, for better debugging and logging)
    @Override
    public String toString() {
        return "GeneralResponse{" +
                "code='" + code + '\'' +
                ", timestamp=" + timestamp +
                ", data='" + data + '\'' +
                '}';
    }
}
