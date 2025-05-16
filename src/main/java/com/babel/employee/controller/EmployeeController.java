package com.babel.employee.controller;

import com.babel.employee.dto.*;
import com.babel.employee.repository.EmployeeRepository;
import com.babel.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Update employee",
            description = "Update employee",
            tags = {"Update employee"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Result.class))
            ),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Result.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Result.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {

        Result<EmployeeResponse> result = this.employeeService.update(employeeUpdateRequest);
        if(result.isSuccessful()) {
            result.setCode(HttpStatus.OK.value());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result.setCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Save list of employers",
            description = "Save list of employers",
            tags = {"Save list of employers"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resource created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListResult.class))
            ),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListResult.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListResult.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<?> saveAll(@Valid @RequestBody RequestDto requestDto) {

        ListResult<EmployeeResponse> result = this.employeeService.saveAll(requestDto);
        if(result.isSuccessful()) {
            result.setCode(HttpStatus.CREATED.value());
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        result.setCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get All employers",
            description = "Get All employers",
            tags = {"Get All employers"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListResult.class))
            ),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListResult.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ListResult.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<?> getAll() {

        ListResult<EmployeeResponse> result = this.employeeService.findAll();
        if(result.isSuccessful()) {
            result.setCode(HttpStatus.OK.value());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result.setCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Delete employee by ID",
            description = "Delete employee by ID",
            tags = {"Delete employee by ID"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource deleted successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Result.class))
            ),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Result.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Result.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {

        Result<String> result = this.employeeService.deleteById(Long.parseLong(id));
        if(result.isSuccessful()) {
            result.setCode(HttpStatus.OK.value());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result.setCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }


}
