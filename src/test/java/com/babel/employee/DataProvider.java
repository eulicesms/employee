package com.babel.employee;

import com.babel.employee.dto.*;
import com.babel.employee.entity.Employee;
import com.babel.employee.entity.JobPosition;
import com.babel.employee.model.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProvider {


    public static List<Employee> getListEmployers() {
        List<Employee> list = new ArrayList<>();

        Employee emp = Employee.builder()
                .id(1L)
                .name("Juan")
                .middleName("Jose")
                .paternalSurname("Vela")
                .maternalSurname("Lopez")
                .age(30)
                .jobPosition(new JobPosition(1L, "Desarroolador"))
                .dateOfBirth(LocalDate.of(2024, 10, 15))
                .sex(Sex.FEMALE)
                .build();

        list.add(emp);

        return list;
    }

    public static List<EmployeeRequest> getEmployedRequestList() {
        List<EmployeeRequest> list = new ArrayList<>();

        EmployeeRequest emp = EmployeeRequest.builder()
                .name("Juan")
                .middleName("Jose")
                .paternalSurname("Vela")
                .maternalSurname("Lopez")
                .age(30)
                .jobPositionId(1L)
                .dateOfBirth("12-08-1999")
                .sex("MALE")
                .build();

        list.add(emp);

        return list;
    }

    public static ListResult<EmployeeResponse> saveAllOK() {

        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(1L)
                .name("Juan")
                .middleName("Jose")
                .paternalSurname("Vela")
                .maternalSurname("Lopez")
                .age(30)
                .jobPosition(new JobPosition(1L, "Desarroolador"))
                .dateOfBirth("12-01-2000")
                .sex(Sex.FEMALE)
                .build();

        List<EmployeeResponse> list = new ArrayList<>();
        list.add(employeeResponse);

        return ListResult.successResult(list, list.size(), "process completed successfully");
    }


    public static Result<EmployeeResponse> update() {
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(1L)
                .name("Juan")
                .middleName("Jose")
                .paternalSurname("Vela")
                .maternalSurname("Lopez")
                .age(30)
                .jobPosition(new JobPosition(1L, "Desarroolador"))
                .dateOfBirth("12-01-2000")
                .sex(Sex.FEMALE)
                .build();
        return Result.successResult(employeeResponse, "OK");
    }

    public static Optional<JobPosition> getJobPositionById() {

        return Optional.of(new JobPosition(2L, "Desarrollador"));

    }

    public static Employee getSaveEmployee() {
        return Employee.builder()
                .id(1L)
                .name("Juan")
                .middleName("Jose")
                .paternalSurname("Vela")
                .maternalSurname("Lopez")
                .age(30)
                .jobPosition(new JobPosition(1L, "Desarroolador"))
                .dateOfBirth(LocalDate.of(2024, 10, 15))
                .sex(Sex.FEMALE)
                .build();
    }

    public static Optional<Employee> findByIdEmployee() {
        Employee employee = Employee.builder()
                .id(1L)
                .name("Juan")
                .middleName("Jose")
                .paternalSurname("Vela")
                .maternalSurname("Lopez")
                .age(30)
                .jobPosition(new JobPosition(1L, "Desarroolador"))
                .dateOfBirth(LocalDate.of(2024, 10, 15))
                .sex(Sex.FEMALE)
                .build();
        return Optional.of(employee);
    }

    public static Optional<Employee> findByIdEmptyEmployee() {
        return Optional.empty();
    }

    public static Optional<JobPosition> getEmptyJobPositionById() {
        return Optional.empty();
    }

    public static ListResult<EmployeeResponse> saveAllError() {
        return ListResult.failedResult(EmployeeResponse.class, "Error");
    }

    public static Result<EmployeeResponse> updateError() {
        return Result.failedResult("Error");
    }

    public static Result<String> getDeleteOk() {
        return Result.successResult( "Successfull");
    }

    public static Result<String> getDeleteError() {
        return Result.failedResult("Error", 404);
    }
}
