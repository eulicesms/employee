package com.babel.employee.dto;

public enum StatusResult {

    FAILED("FAILED"), SUCCEEDED("SUCCEEDED");

    private String desc;

    StatusResult(String desc) {
        this.desc = desc;
    }
}
