package com.babel.employee.dto;

import java.util.List;
import java.util.Optional;

public class ListResult<T> extends Result<T> {

    private Optional<List<T>> values;
    private Integer count;


    public static <T> ListResult<T> failedResult(Class<T> type, String errorMessage) {
        ListResult<T> result = new ListResult<T>();
        result.setStatus(StatusResult.FAILED);
        result.setMessage(errorMessage);
        return result;
    }

    public static <T> ListResult<T> successResult(List<T> t, Integer count, String message) {
        ListResult<T> result = new ListResult<T>();
        result.setStatus(StatusResult.SUCCEEDED);
        result.setValues(Optional.of(t));
        result.setCount(count);
        result.setMessage(message);
        return result;
    }

    public static <T> ListResult<T> emptySuccessResult(Class<T> type) {
        ListResult<T> result = new ListResult<T>();
        result.setStatus(StatusResult.SUCCEEDED);
        result.setValues(Optional.empty());
        return result;
    }

    public Optional<List<T>> getValues() {
        return values;
    }

    public void setValues(Optional<List<T>> values) {
        this.values = values;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean isSuccessful() {
        return this.getStatus().equals(StatusResult.SUCCEEDED) && this.getValues() != null
                && this.getValues().isPresent();
    }

}

