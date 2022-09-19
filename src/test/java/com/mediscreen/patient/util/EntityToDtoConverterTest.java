package com.mediscreen.patient.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.domain.dto.PatientDto;

@SpringBootTest
public class EntityToDtoConverterTest {

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @Test
    @Tag("convertPatientToDto")
    @DisplayName("convertPatientToDto - OK")
    public void givenPatient_whenConvertToDto_thenReturnDto() {
        // GIVEN
        Patient patient = new Patient(1L, "Generic1", "Patient1", "1990-12-31",
                "M", "11 rue albert, 45000 Orleans", "0101010101", "");

        // WHEN
        PatientDto result = entityToDtoConverter.convertPatientToDto(patient);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getBirthdate()).isEqualTo("1990-12-31");
        assertThat(result.getLastName()).isEqualTo("Generic1");
        assertThat(result.getFirstName()).isEqualTo("Patient1");
        assertThat(result.getSex()).isEqualTo("M");
    }

}
