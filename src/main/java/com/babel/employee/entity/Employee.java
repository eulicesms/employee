package com.babel.employee.entity;

import com.babel.employee.model.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String middleName;
    private String paternalSurname;
    private String maternalSurname;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "jobPosition_id")
    private JobPosition jobPosition;

}
