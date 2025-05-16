package com.babel.employee.dto;

import com.babel.employee.entity.JobPosition;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class EmployeeUpdateRequest {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @Size(min = 2, max = 60, message = "name must have a length of between 2 to 60 characters")
    private String name;

    @Size(min = 2, max = 60, message = "middleName must have a length of between 2 to 60 characters")
    private String middleName;

    @Size(min = 2, max = 60, message = "paternalSurname must have a length of between 2 to 60 characters")
    private String paternalSurname;

    @Size(min = 2, max = 60, message = "maternalSurname must have a length of between 2 to 60 characters")
    private String maternalSurname;

    @Min(value = 0, message = "age cannot be less than 0")
    @Max(value = 140, message = "age cannot be greater than 140")
    private Integer age;

    @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "sex can only have the following values (MALE|FEMALE|OTHER)")
    private String sex;

    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])-(0[1-9]|1[0-2])-(\\d{4})$",
            message = "dateOfBirth must be in the format dd-MM-yyyy")
    private String dateOfBirth;

    private Long jobPositionId;

}
