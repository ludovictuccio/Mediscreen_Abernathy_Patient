package com.mediscreen.patient.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.mediscreen.patient.domain.Patient;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql(scripts = "classpath:db-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "classpath:data-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    private static Patient patientGeneric1 = new Patient("Generic1", "Patient1",
            "1990-12-31", "M", "11 rue albert, 45000 Orleans", "0101010101");
    private static Patient patientGeneric2 = new Patient("Generic2", "Patient2",
            "2000-01-15", "F", "22 rue albert, 75000 Paris", "0202020202");

    @Test
    @Tag("findAllPatientByLastName")
    @DisplayName("findAllPatientByLastName - OK")
    public void givenThreePatientsWithTheSameLastname_whenFind_ThenReturnThreeSizeList() {
        // GIVEN
        Patient patientBoyd1 = new Patient("Boyd", "Georges", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101");
        Patient patientBoyd2 = new Patient("Boyd", "Mickael", "200-12-31", "M",
                "11 rue albert, 45000 Orleans", "0645423");
        Patient patientBoyd3 = new Patient("Boyd", "Lydia", "1995-12-31", "F",
                "11 rue albert, 45000 Orleans", "016464");

        // WHEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientBoyd1);
        patientRepository.save(patientBoyd2);
        patientRepository.save(patientBoyd3);
        List<Patient> patientsList = patientRepository.findAll();

        List<Patient> result = patientRepository
                .findAllPatientByLastName("Boyd");

        // THEN
        assertThat(patientsList.size()).isEqualTo(8);
        assertThat(patientRepository.findByLastName("TestNone")).isNotNull();
        assertThat(patientRepository.findByLastName("TestBorderline"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestInDanger"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestEarlyOnset"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("Generic1")).isNotNull();

        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @Tag("FindAllUsers")
    @DisplayName("FindAllUsers - size OK")
    public void givenFourPatientsSavedInDb_whenFindAll_thenReturnCorrectSize() {
        // GIVEN
        // WHEN
        patientRepository.save(patientGeneric1);
        List<Patient> patientsList = patientRepository.findAll();

        // THEN
        assertThat(patientsList.size()).isEqualTo(5);
    }

    @Test
    @Tag("findByLastName")
    @DisplayName("findByLastName - OK")
    public void givenPersonsSavedInDb_whenFindByLastname_thenReturnExistingPersonsFound() {
        // GIVEN
        // WHEN
        patientRepository.save(patientGeneric1);

        // THEN
        assertThat(patientRepository.findByLastName("TestNone")).isNotNull();
        assertThat(patientRepository.findByLastName("TestBorderline"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestInDanger"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestEarlyOnset"))
                .isNotNull();

        assertThat(patientRepository.findByLastName("unknow")).isNull(); // Inexistent
        assertThat(patientRepository.findByLastName("Generic1")).isNotNull(); // saved
        assertThat(patientRepository.findByLastName("Generic2")).isNull(); // not
                                                                           // saved
    }

    @Test
    @Tag("Save")
    @DisplayName("Users saved - OK")
    public void givenTwoNewPatients_whenSavedWithCorrectsValues_thenReturnPatientsSaved() {
        // GIVEN
        // WHEN
        Patient userSaved = patientRepository.save(patientGeneric1);
        Patient userSaved2 = patientRepository.save(patientGeneric2);

        // THEN
        assertThat(userSaved.getId()).isNotNull();
        assertThat(userSaved.getLastName()).isEqualTo("Generic1");
        assertThat(userSaved2.getId()).isNotNull();
        assertThat(userSaved2.getLastName()).isEqualTo("Generic2");
    }

    @Test
    @Tag("FindById")
    @DisplayName("FindById - OK")
    public void givenPatientsInDb_whenFindById_thenReturnCorrectValues() {
        // GIVEN
        // WHEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientGeneric2);
        // THEN
        assertThat(patientRepository.findById(1L)).isNotNull();
        assertThat(patientRepository.findById(2L)).isNotNull();
        assertThat(patientRepository.findById(999L)).isEmpty();
    }
}
