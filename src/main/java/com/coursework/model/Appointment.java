/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Appointment {

    private int appointmentId;
    private String appointmentDate;
    private String doctorName;
    private String patientName;
    private String appointmentReason;

    @JsonCreator
    public Appointment(@JsonProperty("appointmentId") int appointmentId,
                       @JsonProperty("appointmentDate") String appointmentDate,
                       @JsonProperty("doctorName") String doctorName,
                       @JsonProperty("patientName") String patientName,
                       @JsonProperty("appointmentVisitingReason") String appointmentVisitingReason) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
        this.patientName = patientName; 
        this.appointmentReason = appointmentVisitingReason;
    }

    @JsonProperty("appointmentId")
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @JsonProperty("appointmentDate")
    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @JsonProperty("doctorName")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @JsonProperty("appointmentReason")
    public String getAppointmentReason() {
        return appointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }

    @Override
    public String toString() {

        return "Appointment{" +
                " Appointment Id = " + appointmentId +
                ", Appointment Date = " + appointmentDate +
                ", Appointment Patient = " + patientName + 
                ", Appointment Doctor = " + doctorName +   
                ", Appointment Reason='" + appointmentReason + '\'' +
                '}';
    }
}

