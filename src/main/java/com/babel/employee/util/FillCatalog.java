package com.babel.employee.util;

import com.babel.employee.entity.JobPosition;
import com.babel.employee.repository.JobPositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class FillCatalog implements CommandLineRunner {

    private final JobPositionRepository jobPositionRepository;

    @Override
    public void run(String... args) throws Exception {

        if (this.jobPositionRepository.findAll().isEmpty()) {
            this.jobPositionRepository.saveAll(
                    List.of(
                            JobPosition.builder().name("Ingeniero en Redes").build(),
                            JobPosition.builder().name("Ingeniero en Sistemas Jr").build(),
                            JobPosition.builder().name("Ingeniero en Sistemas Sr").build(),
                            JobPosition.builder().name("Recursos Humanos").build(),
                            JobPosition.builder().name("Arquitecto de Soluciones").build(),
                            JobPosition.builder().name("Chofer").build(),
                            JobPosition.builder().name("Ingeniero en Redes Sr").build()
                    )
            );
        }

    }
}
