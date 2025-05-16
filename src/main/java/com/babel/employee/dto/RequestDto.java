package com.babel.employee.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class RequestDto {

    @NotEmpty(message = "List must contain at least one element.")
    public List<@Valid EmployeeRequest> employeers;
}
