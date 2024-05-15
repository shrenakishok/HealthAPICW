/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.exception.ResourceNotFoundException;
import com.coursework.model.MedicalRecord;
import com.coursework.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedicalRecordDAO {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordDAO.class);
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();

    static {
        Patient patient1 = PatientDAO.patients.get(0);
        Patient patient2 = PatientDAO.patients.get(1);

        List<String> diagnoses1 = new ArrayList<>();
        diagnoses1.add("Fever");
        List<String> treatments1 = new ArrayList<>();
        treatments1.add("Rest");
        treatments1.add("Pills");

        medicalRecords.add(new MedicalRecord(patient1, diagnoses1, treatments1));
        
        List<String> diagnoses2 = new ArrayList<>();
        diagnoses2.add("Covid-19");
        List<String> treatments2 = new ArrayList<>();
        treatments2.add("Rest");
        treatments2.add("Medicine");
        medicalRecords.add(new MedicalRecord(patient2, diagnoses2, treatments2));
    }

    public List<MedicalRecord> getAllRecords() {
        logger.info("Fetching All The Medical Records");
        return medicalRecords;
    }

    public MedicalRecord getRecordByPatientId(int patientId) {
        logger.info("Fetching The Medical Record From The Patient with Id: {}", patientId);
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getMedicalsRecord().getPatientId() == patientId) {
                logger.info("The Medical Record Was Found By The Patient with Id: {}", patientId);
                return medicalRecord;
            }
        }
        throw new ResourceNotFoundException("The Medical Record Was Not Found By The Patient Id");
    }

    public void addRecord(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            throw new IllegalArgumentException("There Is An Error In Adding The Medical Record");
        }
        medicalRecords.add(medicalRecord);
        logger.info("The Medical Record Of The Patient Has Been Added: {}", medicalRecord);
    }

    public void updateRecord(MedicalRecord updatedMedicalRecord) {
        if (updatedMedicalRecord == null) {
            throw new IllegalArgumentException("There's An Error In Updating The Medical Record");
        }
        int patientId = updatedMedicalRecord.getMedicalsRecord().getPatientId();
        boolean found = false;
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord medicalRecord = medicalRecords.get(i);
            if (medicalRecord.getMedicalsRecord().getPatientId() == patientId) {
                medicalRecords.set(i, updatedMedicalRecord);
                found = true;
                logger.info("The Medical Record Has Been Updated of The Patient Id {}", patientId);
                break;
            }
        }
        if (!found) {
            throw new ResourceNotFoundException("The Medical Record Of The Patient Was Not Found");
        }
    }

    public void deleteRecord(int patientId) {
        boolean removed = medicalRecords.removeIf(medicalRecord -> medicalRecord.getMedicalsRecord().getPatientId() == patientId);
        if (!removed) {
            throw new ResourceNotFoundException("The Medical Record Has Not Been Deleted");
        }
        logger.info("The Medical Record Of The Patient with ID {} Has Been Deleted Successfully", patientId);
    }
}
