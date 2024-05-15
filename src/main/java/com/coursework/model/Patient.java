/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private int patientId;
    private List<String> patientMedicalHistory;
    private String patientStatus;

    public Patient(int patientId, String personName, int personAge, String personContact, String personAddress, List<String> patientMedicalHistory, String patientStatus) {
        super(patientId, personName, personAge, personContact, personAddress);
        this.patientId = patientId;
        this.patientMedicalHistory = patientMedicalHistory != null ? new ArrayList<>(patientMedicalHistory) : new ArrayList<>();
        this.patientStatus = patientStatus;
    }

    @JsonCreator
    public Patient(@JsonProperty("patientId") int patientId,
                   @JsonProperty("personId") int personId,
                   @JsonProperty("personName") String personName,
                   @JsonProperty("personAge") int personAge,
                   @JsonProperty("personContact") String personContact,
                   @JsonProperty("personAddress") String personAddress,
                   @JsonProperty("patientMedicalHistory") List<String> patientMedicalHistory,
                   @JsonProperty("patientStatus") String patientStatus) {
        super(personId, personName, personAge, personContact, personAddress);
        this.patientId = patientId;
        this.patientMedicalHistory = patientMedicalHistory != null ? new ArrayList<>(patientMedicalHistory) : new ArrayList<>();
        this.patientStatus = patientStatus;
    }

    @JsonProperty("patientId")
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @JsonProperty("patientMedicalHistory")
    public List<String> getPatientMedicalHistory() {
        return patientMedicalHistory;
    }

    public void setPatientMedicalHistory(List<String> patientMedicalHistory) {
        this.patientMedicalHistory = patientMedicalHistory != null ? new ArrayList<>(patientMedicalHistory) : new ArrayList<>();
    }

    @JsonProperty("patientStatus")
    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    @Override
    public String toString() {
        return "Patient : {" +
                "Patient Id ='" + patientId + '\'' +
                ", Patient Name ='" + getPersonName() + '\'' +
                ", Patient Contact Number ='" + getPersonContact() + '\'' +
                ", Patient Address Information ='" + getPersonAddress() + '\'' +
                ", Patient Medical History ='" + patientMedicalHistory + '\'' +
                ", Patient Status ='" + patientStatus + '\'' +
                '}';
    }
}
