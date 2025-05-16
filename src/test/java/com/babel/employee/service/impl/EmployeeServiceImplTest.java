package com.babel.employee.service.impl;

import com.babel.employee.DataProvider;
import com.babel.employee.dto.*;
import com.babel.employee.entity.Employee;
import com.babel.employee.repository.EmployeeRepository;
import com.babel.employee.repository.JobPositionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private JobPositionRepository jobPositionRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testSaveAllOk() {
        RequestDto requestDto = RequestDto.builder()
                .employeers(DataProvider.getEmployedRequestList())
                .build();

        when(this.jobPositionRepository.findById(anyLong())).thenReturn(DataProvider.getJobPositionById());
        when(this.employeeRepository.save(any(Employee.class))).thenReturn(DataProvider.getSaveEmployee());

        ListResult<EmployeeResponse> listResult = this.employeeService.saveAll(requestDto);

        assertNotNull(listResult);
        verify(this.jobPositionRepository).findById(anyLong());
        verify(this.employeeRepository).save(any(Employee.class));

    }

    @Test
    public void testSaveAllError() {
        RequestDto requestDto = RequestDto.builder()
                .employeers(DataProvider.getEmployedRequestList())
                .build();

        when(this.jobPositionRepository.findById(anyLong())).thenReturn(DataProvider.getEmptyJobPositionById());

        ListResult<EmployeeResponse> listResult = this.employeeService.saveAll(requestDto);

        assertNotNull(listResult);
        verify(this.jobPositionRepository).findById(anyLong());

    }

    @Test
    public void testFindAllOK() {

        when(this.employeeRepository.findAll()).thenReturn(DataProvider.getListEmployers());
        ListResult<EmployeeResponse> listResult = this.employeeService.findAll();

        assertNotNull(listResult);
        verify(this.employeeRepository).findAll();
    }

    @Test
    public void testDeleteOK() {

        when(this.employeeRepository.findById(anyLong())).thenReturn(DataProvider.findByIdEmployee());
        doNothing().when(this.employeeRepository).delete(any());

        Result<String> result = this.employeeService.deleteById(anyLong());
        assertNotNull(result);
        verify(this.employeeRepository).findById(anyLong());
        verify(this.employeeRepository).delete(any(Employee.class));
    }

    @Test
    public void testDeleteError() {

        when(this.employeeRepository.findById(anyLong())).thenReturn(DataProvider.findByIdEmptyEmployee());

        Result<String> result = this.employeeService.deleteById(anyLong());
        assertNotNull(result);
        verify(this.employeeRepository).findById(anyLong());
    }

    @Test
    public void testUpdateOk() {

        when(this.employeeRepository.findById(anyLong())).thenReturn(DataProvider.findByIdEmployee());
        when(this.jobPositionRepository.findById(anyLong())).thenReturn(DataProvider.getJobPositionById());
        when(this.employeeRepository.save(any(Employee.class))).thenReturn(DataProvider.getSaveEmployee());

        Result<EmployeeResponse> result = this.employeeService.update(EmployeeUpdateRequest.builder()
                        .id(1L)
                        .name("Peter")
                        .age(34)
                        .middleName("Jose")
                        .maternalSurname("Vela")
                        .paternalSurname("Lopez")
                        .dateOfBirth("12-05-1999")
                        .sex("MALE")
                        .jobPositionId(2L)
                .build());
        assertNotNull(result);
        verify(this.employeeRepository).save(any(Employee.class));
    }

    @Test
    public void testUpdateError() {

        when(this.employeeRepository.findById(anyLong())).thenReturn(DataProvider.findByIdEmptyEmployee());

        Result<EmployeeResponse> result = this.employeeService.update(EmployeeUpdateRequest.builder()
                .id(1L)
                .name("Peter")
                .jobPositionId(2L)
                .build());
        assertNotNull(result);
        verify(this.employeeRepository).findById(anyLong());
    }

    @Test
    public void testUpdateJobPositionError() {

        when(this.employeeRepository.findById(anyLong())).thenReturn(DataProvider.findByIdEmployee());
        when(this.jobPositionRepository.findById(anyLong())).thenReturn(DataProvider.getEmptyJobPositionById());

        Result<EmployeeResponse> result = this.employeeService.update(EmployeeUpdateRequest.builder()
                .id(1L)
                .name("Peter")
                .jobPositionId(2L)
                .build());
        assertNotNull(result);
        verify(this.employeeRepository).findById(anyLong());
        verify(this.jobPositionRepository).findById(anyLong());
    }


}
