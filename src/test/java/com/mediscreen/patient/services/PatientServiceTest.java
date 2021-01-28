package com.mediscreen.patient.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql(scripts = "classpath:db-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "classpath:data-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    private static Patient patientGeneric1 = new Patient("Generic1", "Patient1",
            "1990-12-31", "M", "11 rue albert, 45000 Orleans", "0101010101",
            "");
    private static Patient patientGeneric2 = new Patient("Generic2", "Patient2",
            "2000-01-15", "F", "22 rue albert, 75000 Paris", "0202020202", "");
    private static Patient patientBoyd1 = new Patient("Boyd", "Georges",
            "1990-12-31", "M", "11 rue albert, 45000 Orleans", "0101010101",
            "");
    private static Patient patientBoyd2 = new Patient("Boyd", "Mickael",
            "200-12-31", "M", "11 rue albert, 45000 Orleans", "0645423", "");
    private static Patient patientBoyd3 = new Patient("Boyd", "Lydia",
            "1995-12-31", "F", "11 rue albert, 45000 Orleans", "016464", "");

    @BeforeEach
    public void setUpPerTest() {
        patientRepository.findAll().clear();
    }

    @Test
    @Tag("getAllPatientsWithLastname")
    @DisplayName("getAllPatientsWithLastname - OK - 3 result")
    public void givenThreePatientWithSameLastname_whenGet_thenReturnThreeSizePatientsList() {
        // GIVEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientGeneric2);
        patientRepository.save(patientBoyd1);
        patientRepository.save(patientBoyd2);
        patientRepository.save(patientBoyd3);

        // WHEN
        List<Patient> patientsList = patientRepository.findAll();
        List<Patient> result = patientService
                .getAllPatientsWithSameLastname("Boyd");

        // THEN
        assertThat(patientsList.size()).isEqualTo(9);// 4 in db
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
    @Tag("getAllPatientsWithLastname")
    @DisplayName("getAllPatientsWithLastname - OK - 0 patients")
    public void givenZeroPatientWithSameLastname_whenGet_thenReturnEmptyList() {
        // GIVEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientGeneric2);

        // WHEN
        List<Patient> patientsList = patientRepository.findAll();
        List<Patient> result = patientService
                .getAllPatientsWithSameLastname("Boyd");

        // THEN
        assertThat(patientsList.size()).isEqualTo(6);// 4 in db
        assertThat(patientRepository.findByLastName("TestNone")).isNotNull();
        assertThat(patientRepository.findByLastName("TestBorderline"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestInDanger"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestEarlyOnset"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("Generic1")).isNotNull();

        assertThat(result).isNull();
    }

    @Test
    @Tag("getAllPatientsWithLastname")
    @DisplayName("getAllPatientsWithLastname - ERROR - Empty entry")
    public void givenThreePatientWithSameLastname_whenGetEmptyEntry_thenReturnEmptyList() {
        // GIVEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientGeneric2);
        patientRepository.save(patientBoyd1);
        patientRepository.save(patientBoyd2);
        patientRepository.save(patientBoyd3);

        // WHEN
        List<Patient> patientsList = patientRepository.findAll();
        List<Patient> result = patientService
                .getAllPatientsWithSameLastname("");

        // THEN
        assertThat(patientsList.size()).isEqualTo(9);// 4 in db
        assertThat(patientRepository.findByLastName("TestNone")).isNotNull();
        assertThat(patientRepository.findByLastName("TestBorderline"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestInDanger"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestEarlyOnset"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("Generic1")).isNotNull();

        assertThat(result).isNull();
    }

    @Test
    @Tag("getAllPatientsWithLastname")
    @DisplayName("getAllPatientsWithLastname - ERROR - Number entry")
    public void givenThreePatientWithSameLastname_whenGetWithNumberEntry_thenReturnEmptyList() {
        // GIVEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientGeneric2);
        patientRepository.save(patientBoyd1);
        patientRepository.save(patientBoyd2);
        patientRepository.save(patientBoyd3);

        // WHEN
        List<Patient> patientsList = patientRepository.findAll();
        List<Patient> result = patientService
                .getAllPatientsWithSameLastname("UNKNOW");

        // THEN
        assertThat(patientsList.size()).isEqualTo(9);// 4 in db
        assertThat(patientRepository.findByLastName("TestNone")).isNotNull();
        assertThat(patientRepository.findByLastName("TestBorderline"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestInDanger"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestEarlyOnset"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("Generic1")).isNotNull();

        assertThat(result).isNull();
    }

    @Test
    @Tag("getAllPatientsWithLastname")
    @DisplayName("getAllPatientsWithLastname - ERROR - Special character entry")
    public void givenThreePatientWithSameLastname_whenGetWithSpecialCharacterEntry_thenReturnEmptyList() {
        // GIVEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientGeneric2);
        patientRepository.save(patientBoyd1);
        patientRepository.save(patientBoyd2);
        patientRepository.save(patientBoyd3);

        // WHEN
        List<Patient> patientsList = patientRepository.findAll();
        List<Patient> result = patientService
                .getAllPatientsWithSameLastname("*-");

        // THEN
        assertThat(patientsList.size()).isEqualTo(9);// 4 in db
        assertThat(patientRepository.findByLastName("TestNone")).isNotNull();
        assertThat(patientRepository.findByLastName("TestBorderline"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestInDanger"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestEarlyOnset"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("Generic1")).isNotNull();

        assertThat(result).isNull();
    }

    @Test
    @Tag("getAllPatientsWithLastname")
    @DisplayName("getAllPatientsWithLastname - ERROR - Size 1")
    public void givenThreePatientWithSameLastname_whenGetWithOneSizeEntry_thenReturnEmptyList() {
        // GIVEN
        patientRepository.save(patientGeneric1);
        patientRepository.save(patientGeneric2);
        patientRepository.save(patientBoyd1);
        patientRepository.save(patientBoyd2);
        patientRepository.save(patientBoyd3);

        // WHEN
        List<Patient> patientsList = patientRepository.findAll();
        List<Patient> result = patientService
                .getAllPatientsWithSameLastname("p");

        // THEN
        assertThat(patientsList.size()).isEqualTo(9);// 4 in db
        assertThat(patientRepository.findByLastName("TestNone")).isNotNull();
        assertThat(patientRepository.findByLastName("TestBorderline"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestInDanger"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("TestEarlyOnset"))
                .isNotNull();
        assertThat(patientRepository.findByLastName("Generic1")).isNotNull();

        assertThat(result).isNull();
    }

    @Test
    @Tag("getPatientMedicalRecord")
    @DisplayName("getPatientMedicalRecord - OK - Existing id")
    public void givenPatients_whenGetMedicalRecordWithCorrectID_thenReturnHisId() {
        // GIVEN
        // WHEN
        Patient result = patientService.getPatientMedicalRecord(1L);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getLastName()).isEqualTo("TestNone");
        assertThat(result.getFirstName()).isEqualTo("Test");
    }

    @Test
    @Tag("getPatientMedicalRecord")
    @DisplayName("getPatientMedicalRecord - Error - Non-existant id")
    public void givenPatients_whenGetMedicalRecordWithIncorrectID_thenReturnNull() {
        // GIVEN
        // WHEN
        Patient result = patientService.getPatientMedicalRecord(444L);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    @Tag("updateMedicalRecord")
    @DisplayName("updateMedicalRecord - OK")
    public void givenMedicalRecord_whenUpdate_thenReturnUpdated() {
        // GIVEN
        patientService.addPatient(patientGeneric1);
        Patient patientUpdated = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "other address", "11111", "usename");

        // WHEN
        boolean result = patientService.updateMedicalRecord(patientUpdated);

        List<Patient> patientsList = patientRepository.findAll();

        // THEN
        assertThat(result).isTrue();
        assertThat(patientsList.size()).isEqualTo(5);// 4 in db
    }

    @Test
    @Tag("addPatient")
    @DisplayName("addPatient - Ok")
    public void givenNonExistantPatient_whenAdd_thenReturnAdded() {
        // GIVEN
        Patient patientToAdd = new Patient("New", "Patient", "1990-12-31", "M",
                "other address", "11111", "usename");

        // WHEN
        Patient result = patientService.addPatient(patientToAdd);

        List<Patient> patientsList = patientRepository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(patientsList.size()).isEqualTo(5);// +1
        assertThat(result.getId()).isEqualTo(5L);
    }

    @Test
    @Tag("addPatient")
    @DisplayName("addPatient - Error - Already exists")
    public void givenExistingPatient_whenAdd_thenReturnNull() {
        // GIVEN
        patientRepository.save(patientGeneric1);
        Patient patientToAdd = new Patient("Generic1", "Patient1", "1990-12-31",
                "M", "other address", "11111", "usename");

        // WHEN
        Patient result = patientService.addPatient(patientToAdd);

        List<Patient> patientsList = patientRepository.findAll();

        // THEN
        assertThat(result).isNull();
        assertThat(patientsList.size()).isEqualTo(5);// Not Changed
    }

}
