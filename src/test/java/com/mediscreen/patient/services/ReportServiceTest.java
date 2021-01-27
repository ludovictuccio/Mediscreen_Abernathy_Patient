package com.mediscreen.patient.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.domain.dto.PatientDto;
import com.mediscreen.patient.repository.PatientRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql(scripts = "classpath:db-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "classpath:data-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private PatientRepository patientRepository;

    private static Patient patientGeneric1 = new Patient(1L, "Generic1",
            "Patient1", "1990-12-31", "M", "11 rue albert, 45000 Orleans",
            "0101010101", "");
    private static Patient patientGeneric2 = new Patient(2L, "Generic2",
            "Patient2", "2000-01-15", "F", "22 rue albert, 75000 Paris",
            "0202020202", "");

    @Test
    @Tag("getPatientDto")
    @DisplayName("getPatientDto - OK")
    public void aaaa() {
        // GIVEN

        // WHEN
        PatientDto result = reportService.getPatientDto(patientGeneric1);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getBirthdate()).isEqualTo("1990-12-31");
        assertThat(result.getLastName()).isEqualTo("Generic1");
        assertThat(result.getFirstName()).isEqualTo("Patient1");
        assertThat(result.getSex()).isEqualTo("M");
    }

}
