/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.exception.UserNotFoundException;
import com.coursework.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientDAO {

    private static final Logger logger = LoggerFactory.getLogger(PatientDAO.class);
    static List<Patient> patients = new ArrayList<>();

    static {
        List<String> medicalHistory1 = new ArrayList<>();
        medicalHistory1.add("Dengue - 2024");
        medicalHistory1.add("Cold - 2021");
        patients.add(new Patient(1, "Aaron", 20, "0778897301", "3B, Heights, Laveder Street", medicalHistory1, "Healthy"));

        List<String> medicalHistory2 = new ArrayList<>();
        medicalHistory2.add("Checkup - 2024");
        medicalHistory2.add("Covid - 2020");
        patients.add(new Patient(2, "Jordan", 19, "0764457910", "121, Origami Street", medicalHistory2, "Healthy"));
    }

    public List<Patient> getAllPatients() {
        logger.info("Fetching All The Patients From The Array List");
        return patients;
    }

    public Patient getPatientById(int patientId) {
        logger.info("Fetching The Patient By The Id {}", patientId);
        for (Patient patient : patients) {
            if (patient.getPatientId() == patientId) {
                logger.info("The Patient With The Id {} Has Been Found", patientId);
                return patient;
            }
        }
        throw new UserNotFoundException("The Patient With The Id " + patientId + " Has Not Been Found");
    }

    public void addPatient(Patient patient) {
        if (patient == null || patient.getPersonName() == null || patient.getPersonName().isEmpty()) {
            throw new IllegalArgumentException("There's An Error In Adding The Patient");
        }
        patients.add(patient);
        logger.info("The Patient Has Been Added: {}", patient);
    }

    public void updatePatient(Patient updatedPatient) {
        if (updatedPatient == null) {
            throw new IllegalArgumentException("There's An Error In Updating The Patient");
        }

        int updatedPatientId = updatedPatient.getPatientId();
        boolean found = false;

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getPatientId() == updatedPatientId) {
                patients.set(i, updatedPatient);
                found = true;
                logger.info("The Patient Has Been Updated: {}", updatedPatient);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The Patient With The Id " + updatedPatient.getPatientId() + " Has Not Been Found");
        }
    }

    public void deletePatient(int id) {
        boolean removed = patients.removeIf(patient -> patient.getPatientId() == id);

        if (!removed) {
            throw new IllegalArgumentException("The Patient With The Id " + id + " Has Not Been Found");
        }
        logger.info("The Patient With The Id {} Has Been Deleted Successfully", id);
    }
    
}
