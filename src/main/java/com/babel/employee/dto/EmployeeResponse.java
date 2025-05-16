package com.babel.employee.dto;

import com.babel.employee.entity.JobPosition;
import com.babel.employee.model.Sex;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

    private Long id;

    private String name;

    private String middleName;

    private String paternalSurname;

    private String maternalSurname;

    private Integer age;

    private Sex sex;

    private String dateOfBirth;

    private JobPosition jobPosition;
}
