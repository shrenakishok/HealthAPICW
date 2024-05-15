/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {

    private Patient patientMedicalsRecord;
    private List<String> patientDiagnosesRecord;
    private List<String> patientTreatmentsRecord;

    @JsonCreator
    public MedicalRecord(@JsonProperty("patientMedicalsRecord") Patient patientMedicalsRecord,
                         @JsonProperty("patientDiagnosesRecord") List<String> patientDiagnosesRecord,
                         @JsonProperty("patientTreatmentsRecord") List<String> patientTreatmentsRecord) {
        this.patientMedicalsRecord = patientMedicalsRecord;
        this.patientDiagnosesRecord = new ArrayList<>(patientDiagnosesRecord);
        this.patientTreatmentsRecord = new ArrayList<>(patientTreatmentsRecord);
    }

    public Patient getMedicalsRecord() {
        return patientMedicalsRecord;
    }

    public List<String> getDiagnosesRecord() {
        return patientDiagnosesRecord;
    }

    public List<String> getTreatmentsRecord() {
        return patientTreatmentsRecord;
    }

    public void setMedicalsRecord(Patient patientMedicalsRecord) {
        this.patientMedicalsRecord = patientMedicalsRecord;
    }

    public void setDiagnosesRecord(List<String> patientDiagnosesRecord) {
        this.patientDiagnosesRecord = new ArrayList<>(patientDiagnosesRecord);
    }

    public void setTreatmentsRecord(List<String> patientTreatmentsRecord) {
        this.patientTreatmentsRecord = new ArrayList<>(patientTreatmentsRecord);
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "Patient Id = " + patientMedicalsRecord.getPatientId() +
                ", Patient Medical Record = " + patientMedicalsRecord +
                ", Patient Treatments Record = " + patientTreatmentsRecord +
                '}';
    }
}
