package com.intecbrussel.commonsservice.dto;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class IndividualDTO {

    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String address;
    private Date birthDate;
    private String phoneNumber;
    @NotNull
    @Email(regexp = "^(.+)@(\\S+)$", message = " Please provide a valid email")
    private String emailAddress;
    @NotNull
    @Pattern(regexp = "^[0-9]{11}$", message = " Please provide 11 cyphers")
    private String rrn;
    private String employerName;
    private String occupation;



    public void updateWith(IndividualDTO other) {
        if (other.id != null) {
            this.setId(other.getId());
        }
        if (other.firstName != null){
            this.setFirstName(other.getFirstName());
        }
        if (other.lastName != null) {
            this.setLastName(other.getLastName());
        }
        if (other.address != null) {
            this.setAddress(other.getAddress());
        }
        if (other.birthDate != null) {
            this.setBirthDate(other.getBirthDate());
        }
        if (other.phoneNumber != null) {
            this.setPhoneNumber(other.getPhoneNumber());
        }
        if (other.emailAddress != null) {
            this.setEmailAddress(other.getEmailAddress());
        }
        if (other.rrn != null) {
            this.setRrn(other.getRrn());
        }
        if (other.emailAddress != null) {
            this.setEmployerName(other.getEmployerName());
        }
        if (other.occupation != null) {
            this.setOccupation(other.getOccupation());
        }
    }
}
