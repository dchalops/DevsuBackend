package com.devsu.clientspersonsservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private int code;
    private T data;


    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("success", message, HttpStatus.OK.value(), data);
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status, T data) {
        return new ApiResponse<>("error", message, status.value(), data);
    }
}
