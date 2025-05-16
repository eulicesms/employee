package com.babel.employee.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Setter
@Getter
public class Result <T> {

    private StatusResult status;
    private Optional<T> data;
    private String message;
    private Integer code;

    public static <T> Result<T> failedResult(String errorMessage) {
        Result<T> Result = new Result<T>();
        Result.setStatus(StatusResult.FAILED);
        Result.setMessage(errorMessage);
        return Result;
    }

    public static <T> Result<T> failedResult(String errorMessage, Integer code) {
        Result<T> Result = new Result<T>();
        Result.setStatus(StatusResult.FAILED);
        Result.setMessage(errorMessage);
        Result.setCode(code);
        return Result;
    }

    public static <T> Result<T> successResult(T t) {
        Result<T> Result = new Result<T>();
        Result.setStatus(StatusResult.SUCCEEDED);
        Result.setCode(HttpStatus.OK.value());
        Result.setData(Optional.of(t));
        return Result;
    }

    public static <T> Result<T> successResult(T t, String mensaje) {
        Result<T> Result = new Result<T>();
        Result.setStatus(StatusResult.SUCCEEDED);
        Result.setCode(HttpStatus.OK.value());
        Result.setMessage(mensaje);
        Result.setData(Optional.of(t));
        return Result;
    }

    public boolean isSuccessful() {
        return this.getStatus().equals(StatusResult.SUCCEEDED);
    }

}
