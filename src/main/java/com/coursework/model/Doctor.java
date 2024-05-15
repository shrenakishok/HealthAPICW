/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctor extends Person {

    private int doctorId;
    private String doctorSpecialization;
    
    public Doctor(int doctorId, String personName, int personAge, String personContact, String personAddress, String doctorSpecialization) {
        super(doctorId, personName, personAge, personContact, personAddress);
        this.doctorId = doctorId;
        this.doctorSpecialization = doctorSpecialization;
    }

    @JsonCreator
    public Doctor(@JsonProperty("doctorId") int doctorId,
            @JsonProperty("personId") int personId,
            @JsonProperty("personName") String personName,
            @JsonProperty("personAge") int personAge,
            @JsonProperty("personContact") String personContact,
            @JsonProperty("personAddress") String personAddress,
            @JsonProperty("doctorSpecialization") String doctorSpecialization) {
        super(personId, personName, personAge, personContact, personAddress);
        this.doctorId = doctorId;
        this.doctorSpecialization = doctorSpecialization;
    }

    @JsonProperty("doctorId")
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @JsonProperty("doctorSpecialization")
    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }
}
