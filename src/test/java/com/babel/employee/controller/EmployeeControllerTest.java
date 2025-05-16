package com.babel.employee.controller;

import com.babel.employee.DataProvider;
import com.babel.employee.dto.EmployeeUpdateRequest;
import com.babel.employee.dto.RequestDto;
import com.babel.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    public void saveAllTest() throws Exception {
        RequestDto requestDto = RequestDto.builder()
                .employeers(DataProvider.getEmployedRequestList())
                .build();

        when(this.employeeService.saveAll(any(RequestDto.class))).thenReturn(DataProvider.saveAllOK());
        mockMvc.perform(post("/api/employee").content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveAllErrorTest() throws Exception {
        RequestDto requestDto = RequestDto.builder()
                .employeers(DataProvider.getEmployedRequestList())
                .build();

        when(this.employeeService.saveAll(any(RequestDto.class))).thenReturn(DataProvider.saveAllError());
        mockMvc.perform(post("/api/employee").content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void getAllTest() throws Exception {

        when(this.employeeService.findAll()).thenReturn(DataProvider.saveAllOK());
        mockMvc.perform(get("/api/employee"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllErrorTest() throws Exception {
        when(this.employeeService.findAll()).thenReturn(DataProvider.saveAllError());
        mockMvc.perform(get("/api/employee"))
                .andExpect(status().is(404));
    }

    @Test
    public void updateTestOK() throws Exception {
        EmployeeUpdateRequest employeeUpdateRequest = EmployeeUpdateRequest.builder()
                .id(1L)
                .name("Peter")
                .age(34)
                .middleName("Jose")
                .maternalSurname("Vela")
                .paternalSurname("Lopez")
                .dateOfBirth("12-05-1999")
                .sex("MALE")
                .jobPositionId(2L)
                .build();
        when(this.employeeService.update(any(EmployeeUpdateRequest.class))).thenReturn(DataProvider.update());
        mockMvc.perform(put("/api/employee").content(objectMapper.writeValueAsString(employeeUpdateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTestError() throws Exception {
        EmployeeUpdateRequest employeeUpdateRequest = EmployeeUpdateRequest.builder()
                .id(1L)
                .name("Peter")
                .age(34)
                .middleName("Jose")
                .maternalSurname("Vela")
                .paternalSurname("Lopez")
                .dateOfBirth("12-05-1999")
                .sex("MALE")
                .jobPositionId(2L)
                .build();
        when(this.employeeService.update(any(EmployeeUpdateRequest.class))).thenReturn(DataProvider.updateError());
        mockMvc.perform(put("/api/employee").content(objectMapper.writeValueAsString(employeeUpdateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void deleteOkTest() throws Exception {

        when(this.employeeService.deleteById(anyLong())).thenReturn(DataProvider.getDeleteOk());
        mockMvc.perform(delete("/api/employee/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteErrorTest() throws Exception {

        when(this.employeeService.deleteById(anyLong())).thenReturn(DataProvider.getDeleteError());
        mockMvc.perform(delete("/api/employee/1"))
                .andExpect(status().is(404));

    }
}
