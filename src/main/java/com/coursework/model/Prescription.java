/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
    
    private int prescriptionId;
    private int prescriptionAppointmentNo;
    private String prescribedPatient;
    private String prescribingDoctor;
    private String prescriptionName;
    private String prescriptionDosage;
    private String prescribedDate;
    private List<String> prescriptionInstructions;

    @JsonCreator
    public Prescription(@JsonProperty("prescriptionId") int prescriptionId,
                        @JsonProperty("prescriptionAppointmentNo") int prescriptionAppointmentNo,
                        @JsonProperty("prescribedPatient") String prescribedPatient,
                        @JsonProperty("prescribingDoctor") String prescribingDoctor,
                        @JsonProperty("prescriptionName") String prescriptionName,
                        @JsonProperty("prescriptionDosage") String prescriptionDosage,
                        @JsonProperty("prescribedDate") String prescribedDate,
                        @JsonProperty("prescriptionInstructions") List<String> prescriptionInstructions) {
        this.prescriptionId = prescriptionId;
        this.prescriptionAppointmentNo = prescriptionAppointmentNo;
        this.prescribedPatient = prescribedPatient;
        this.prescribingDoctor = prescribingDoctor;
        this.prescriptionName = prescriptionName;
        this.prescriptionDosage = prescriptionDosage;
        this.prescribedDate = prescribedDate;
        this.prescriptionInstructions = prescriptionInstructions != null ? new ArrayList<>(prescriptionInstructions) : new ArrayList<>();
    }
    
    @JsonProperty("prescriptionId")
    public int getPrescriptionId() {
        return prescriptionId;
    }
    
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    @JsonProperty("prescriptionAppointmentNumber")
    public int getPrescriptionAppointmentNo() {
        return prescriptionAppointmentNo;
    }

    public void setPrescriptionAppointmentNo(int prescriptionAppointmentNo) {
        this.prescriptionAppointmentNo = prescriptionAppointmentNo;
    }

    @JsonProperty("prescribedPatient")
    public String getPrescribedPatient() {
        return prescribedPatient;
    }

    public void setPrescribedPatient(String prescribedPatient) {
        this.prescribedPatient = prescribedPatient;
    }

    @JsonProperty("prescribingDoctor")
    public String getPrescribingDoctor() {
        return prescribingDoctor;
    }

    public void setPrescribingDoctor(String prescribingDoctor) {
        this.prescribingDoctor = prescribingDoctor;
    }

    @JsonProperty("prescriptionName")
    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    @JsonProperty("prescriptionDosage")
    public String getPrescriptionDosage() {
        return prescriptionDosage;
    }

    public void setPrescriptionDosage(String prescriptionDosage) {
        this.prescriptionDosage = prescriptionDosage;
    }

    @JsonProperty("prescribedDate")
    public String getPrescribedDate() {
        return prescribedDate;
    }

    public void setPrescribedDate(String prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    @JsonProperty("prescriptionInstructions")
    public List<String> getPrescriptionInstructions() {
        return prescriptionInstructions;
    }

    public void setPrescriptionInstructions(List<String> prescriptionInstructions) {
        this.prescriptionInstructions = prescriptionInstructions != null ? new ArrayList<>(prescriptionInstructions) : new ArrayList<>();
    }
}