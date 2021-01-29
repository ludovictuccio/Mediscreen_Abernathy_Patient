package com.mediscreen.patient.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = -5987711516877850452L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id = System.nanoTime();

    @NotBlank(message = "LastName is mandatory")
    @Size(min = Constants.NAME_MIN_SIZE, max = Constants.LASTNAME_MAX_SIZE)
    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = "FirstName is mandatory")
    @Size(min = Constants.NAME_MIN_SIZE, max = Constants.FIRSTNAME_MAX_SIZE)
    @Column(name = "firstname")
    private String firstName;

    @NotBlank(message = "Birthdate is mandatory")
    @JsonFormat(pattern = Constants.DATE_PATTERN)
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Column(name = "birthdate")
    private String birthdate;

    @NotBlank(message = "Sex is mandatory (M or F)")
    @Size(max = Constants.SEX_MAX_SIZE, message = "Sex is mandatory (M or F)")
    @Column(name = "sex")
    @Pattern(regexp = "^[M|F]{1}$", message = "Must be M or F")
    private String sex;

    @Size(max = Constants.ADDRESS_MAX_SIZE)
    @Column(name = "address")
    private String address;

    @Size(max = Constants.PHONE_MAX_SIZE)
    @Column(name = "phone")
    private String phone;

    @Size(max = Constants.USE_NAME_MAX_SIZE)
    @Column(name = "usename")
    private String useName;

    public Patient(
            @NotBlank(message = "LastName is mandatory") @Size(min = Constants.NAME_MIN_SIZE, max = Constants.LASTNAME_MAX_SIZE) final String plastName,
            @NotBlank(message = "FirstName is mandatory") @Size(min = Constants.NAME_MIN_SIZE, max = Constants.FIRSTNAME_MAX_SIZE) final String pfirstName,
            @NotBlank(message = "Birthdate is mandatory") final String pbirthdate,
            @NotBlank(message = "Sex is mandatory (M or F)") @Size(max = Constants.SEX_MAX_SIZE, message = "Sex is mandatory (M or F)") @Pattern(regexp = "^[M|F]{1}$", message = "Must be M or F") final String psex,
            @Size(max = Constants.ADDRESS_MAX_SIZE) final String paddress,
            @Size(max = Constants.PHONE_MAX_SIZE) final String pphone,
            @Size(max = Constants.USE_NAME_MAX_SIZE) final String puseName) {
        super();
        this.lastName = plastName;
        this.firstName = pfirstName;
        this.birthdate = pbirthdate;
        this.sex = psex;
        this.address = paddress;
        this.phone = pphone;
        this.useName = puseName;
    }

}
