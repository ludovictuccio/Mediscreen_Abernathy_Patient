package com.mediscreen.patient.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.patient.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Patient model class.
 *
 * @author Ludovic Tuccio
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = -1916387738453111224L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 2, max = Constants.LASTNAME_MAX_SIZE)
    @Column(name = "lastname")
    private String lastName;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 2, max = Constants.FIRSTNAME_MAX_SIZE)
    @Column(name = "firstname")
    private String firstName;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @JsonFormat(pattern = Constants.DATE_PATTERN)
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Column(name = "birthdate")
    private String birthdate;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 1)
    @Column(name = "sex")
    private char sex;

    @Getter
    @Setter
    @Size(max = Constants.ADDRESS_MAX_SIZE)
    @Column(name = "address")
    private String address;

    @Getter
    @Setter
    @Size(max = Constants.PHONE_MAX_SIZE)
    @Column(name = "phone")
    private String phone;

    @Getter
    @Setter
    @Size(max = Constants.USE_NAME_MAX_SIZE)
    @Column(name = "usename")
    private String useName;

//    @Setter
//    @Column(name = "age")
//    private int age;
//
//    public int getAge() {
//        int patientAge = AgeCalculator.ageCalculation(this.birthdate);
//        return patientAge;
//    }

    public Patient(@NotNull @NotEmpty @Size(min = 2, max = 100) String lastName,
            @NotNull @NotEmpty @Size(min = 2, max = 200) String firstName,
            @NotNull @NotEmpty String birthdate,
            @NotNull @NotEmpty @Size(min = 1, max = 1) char sex,
            @Size(max = 225) String address, @Size(max = 30) String phone) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

}
