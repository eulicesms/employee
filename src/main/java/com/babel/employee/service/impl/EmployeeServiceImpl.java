package com.babel.employee.service.impl;

import com.babel.employee.dto.*;
import com.babel.employee.entity.Employee;
import com.babel.employee.entity.JobPosition;
import com.babel.employee.model.Sex;
import com.babel.employee.repository.EmployeeRepository;
import com.babel.employee.repository.JobPositionRepository;
import com.babel.employee.service.EmployeeService;
import com.babel.employee.util.UtilDate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobPositionRepository jobPositionRepository;

    @Override
    public ListResult<EmployeeResponse> saveAll(RequestDto requestDto) {

        List<EmployeeResponse> listResult = new ArrayList<>();
        requestDto.getEmployeers().forEach(item -> {
            Optional<EmployeeResponse> employeeResponse = this.save(item);
            employeeResponse.ifPresent(listResult::add);
        });
        log.info("process completed successfully");
        return ListResult.successResult(listResult, listResult.size(), "process completed successfully");
    }

    @Override
    public ListResult<EmployeeResponse> findAll() {

        List<EmployeeResponse> listResult = new ArrayList<>();
           this.employeeRepository.findAll().forEach(item -> {
            listResult.add(convertEmployeeToResponse(item));
        });
        return ListResult.successResult(listResult, listResult.size(), "process completed successfully");
    }

    @Override
    public Result<String> deleteById(Long id) {
        Optional<Employee> employeeOptional = this.employeeRepository.findById(id);
        if(employeeOptional.isEmpty())
            return Result.failedResult("The Resource with the ID "+ id + " does not exist", 404);

        this.employeeRepository.delete(employeeOptional.get());

        return Result.successResult( "Resource successfully deleted");
    }

    @Override
    public Result<EmployeeResponse> update(EmployeeUpdateRequest employeeRequest) {

        Optional<Employee> employeeOptional = this.employeeRepository.findById(employeeRequest.getId());
        if(employeeOptional.isEmpty())
            return Result.failedResult("The employee does not exist", 404);

        Optional<JobPosition> jobPositionOptional = Optional.empty();
        if (employeeRequest.getJobPositionId() != null) {
            jobPositionOptional = this.jobPositionRepository.findById(employeeRequest.getJobPositionId());
            if(jobPositionOptional.isEmpty())
                return Result.failedResult("The Job Position does not exist", 404);
        }

        Employee updatedEmployee = this.employeeRepository.save(convertResponseToEmployee(employeeOptional.get(), employeeRequest, jobPositionOptional));
        return Result.successResult(convertEmployeeToResponse(updatedEmployee), "Employee updated successfully");
    }

    @Override
    public Optional<EmployeeResponse> save(EmployeeRequest employeeRequest) {

        Optional<JobPosition> jobPositionOptional = jobPositionRepository.findById(employeeRequest.getJobPositionId());

        if(jobPositionOptional.isEmpty())
            return Optional.empty();

        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .middleName(employeeRequest.getMiddleName())
                .paternalSurname(employeeRequest.getPaternalSurname())
                .maternalSurname(employeeRequest.getMaternalSurname())
                .jobPosition(jobPositionOptional.get())
                .age(employeeRequest.getAge())
                .sex(Sex.valueOf(employeeRequest.getSex()))
                .dateOfBirth(UtilDate.convertStringToLocalDate(employeeRequest.getDateOfBirth()))
                .build();

        return Optional.ofNullable(convertEmployeeToResponse(employeeRepository.save(employee)));
    }

    private EmployeeResponse convertEmployeeToResponse(Employee employee) {

        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .middleName(employee.getMiddleName())
                .paternalSurname(employee.getPaternalSurname())
                .maternalSurname(employee.getMaternalSurname())
                .age(employee.getAge())
                .dateOfBirth(UtilDate.convertLocalDateToString(employee.getDateOfBirth()))
                .sex(employee.getSex())
                .jobPosition(employee.getJobPosition())
                .build();
    }

    private Employee convertResponseToEmployee(Employee employee, EmployeeUpdateRequest employeeRequest, Optional<JobPosition> jobPositionOptional) {

        return Employee.builder()
                .id(employeeRequest.getId())
                .name(employeeRequest.getName() == null ? employee.getName(): employeeRequest.getName())
                .middleName(employeeRequest.getMiddleName() == null ? employee.getMiddleName(): employeeRequest.getMiddleName())
                .paternalSurname(employeeRequest.getPaternalSurname() == null ? employee.getPaternalSurname(): employeeRequest.getPaternalSurname())
                .maternalSurname(employeeRequest.getMaternalSurname() == null ? employee.getMaternalSurname() : employeeRequest.getMaternalSurname())
                .age(employeeRequest.getAge() == null ? employee.getAge() : employeeRequest.getAge())
                .sex(employeeRequest.getSex() == null ? employee.getSex() : Sex.valueOf(employeeRequest.getSex()))
                .jobPosition(jobPositionOptional.orElseGet(employee::getJobPosition))
                .dateOfBirth(employeeRequest.getDateOfBirth() == null ? employee.getDateOfBirth() : UtilDate.convertStringToLocalDate(employeeRequest.getDateOfBirth()))
                .build();
    }
}
