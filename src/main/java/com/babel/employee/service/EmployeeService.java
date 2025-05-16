package com.babel.employee.service;

import com.babel.employee.dto.*;
import jakarta.validation.Valid;

import java.util.Optional;

public interface EmployeeService {

    Optional<EmployeeResponse> save(EmployeeRequest employeeRequest);

    ListResult<EmployeeResponse> saveAll(RequestDto requestDto);

    ListResult<EmployeeResponse> findAll();

    Result<String> deleteById(Long id);

    Result<EmployeeResponse> update(EmployeeUpdateRequest employeeUpdateRequest);
}
