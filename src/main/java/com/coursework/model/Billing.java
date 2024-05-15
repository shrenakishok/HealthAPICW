/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Billing {

    private int billingId;
    private int billingAppointmentId;
    private String billingAppointmentsDate;
    private List<String> billingInvoicesRecord;
    private List<String> billingPaymentsRecord;

    @JsonCreator
    public Billing(@JsonProperty("billingId") int billingId,
                   @JsonProperty("billingAppointmentId") int billingAppointmentId,
                   @JsonProperty("billingAppointmentsDate") String billingAppointmentsDate,
                   @JsonProperty("billingInvoicesRecord") List<String> billingInvoicesRecord,
                   @JsonProperty("billingPaymentsRecord") List<String> billingPaymentsRecord) {
        this.billingId = billingId;
        this.billingAppointmentId = billingAppointmentId;
        this.billingAppointmentsDate = billingAppointmentsDate;
        this.billingInvoicesRecord = billingInvoicesRecord != null ? new ArrayList<>(billingInvoicesRecord) : new ArrayList<>();
        this.billingPaymentsRecord = billingPaymentsRecord != null ? new ArrayList<>(billingPaymentsRecord) : new ArrayList<>();
    }

    public int getBillingId() {
        return billingId;
    }

    public int getBillingAppointmentId() {
        return billingAppointmentId;
    }

    public String getBillingAppointmentsDate() {
        return billingAppointmentsDate;
    }

    public List<String> getBillingInvoicesRecord() {
        return billingInvoicesRecord;
    }

    public List<String> getBillingPaymentsRecord() {
        return billingPaymentsRecord;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public void setBillingAppointmentIdentificationNumber(int billingAppointmentId) {
        this.billingAppointmentId = billingAppointmentId;
    }

    public void setBillingAppointmentsDate(String billingAppointmentsDate) {
        this.billingAppointmentsDate = billingAppointmentsDate;
    }

    public void setBillingInvoicesRecord(List<String> billingInvoicesRecord) {
        this.billingInvoicesRecord = billingInvoicesRecord != null ? new ArrayList<>(billingInvoicesRecord) : new ArrayList<>();
    }

    public void setBillingPaymentsRecord(List<String> billingPaymentsRecord) {
        this.billingPaymentsRecord = billingPaymentsRecord != null ? new ArrayList<>(billingPaymentsRecord) : new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "Patient : {" +
                "Billing Id ='" + billingId + '\'' +
                ", Billing Appointment Id ='" + billingAppointmentId + '\'' +
                ", Billing Appointment Date ='" + billingAppointmentsDate + '\'' +
                ", Billing Invoices Record ='" + billingInvoicesRecord + '\'' +
                ", Billing Payments Record ='" + billingPaymentsRecord + '\'' +
                '}';
    }

}