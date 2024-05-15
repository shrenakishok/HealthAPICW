/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrescriptionDAO {

    private static final Logger logger = LoggerFactory.getLogger(PrescriptionDAO.class);
    private static List<Prescription> prescriptions = new ArrayList<>();
    
    static {
        List<String> instructions = new ArrayList<>();
        instructions.add("Take two tablets per day after meals");
        instructions.add("Crush tablets and mix with water");

        Prescription prescription = new Prescription(
                1,
                1,
                "Aaron",
                "Chris",
                "Medicine",
                "15mg",
                "2024-03-21",
                instructions
        );

        prescriptions.add(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        logger.info("Fetching All The Prescriptions From The Array List");
        return prescriptions;
    }

    public Prescription getPrescriptionById(int prescriptionId) {
        logger.info("Fetching The Prescription By Prescription ID: {}", prescriptionId);
        for (Prescription prescription : prescriptions) {
            if (prescription.getPrescriptionId() == prescriptionId) {
                logger.info("The Prescription Was Found By Prescription ID: {}", prescriptionId);
                return prescription;
            }
        }
        logger.info("The Prescription Was Not Found By ID: {}", prescriptionId);
        return null;
    }

    public void addPrescription(Prescription prescription) {
        if (prescription == null || prescription.getPrescriptionId() == 0) {
            throw new IllegalArgumentException("There's An Error In Adding The Prescription");
        }
        prescriptions.add(prescription);
        logger.info("The Prescription Has Been Added: {}", prescription);
    }

    public void updatePrescription(Prescription updatedPrescription) {
        if (updatedPrescription == null || updatedPrescription.getPrescriptionId() == 0) {
            throw new IllegalArgumentException("There's An Error In Updating The Prescription");
        }
        int prescriptionId = updatedPrescription.getPrescriptionId();
        boolean found = false;
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            if (prescription.getPrescriptionId() == prescriptionId) {
                prescriptions.set(i, updatedPrescription);
                found = true;
                logger.info("The Prescription Has Been Updated: {}", updatedPrescription);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The Prescription With The ID Was Not Found");
        }
    }

    public void deletePrescription(int prescriptionId) {
        boolean removed = prescriptions.removeIf(prescription -> prescription.getPrescriptionId() == prescriptionId);
        if (!removed) {
            throw new IllegalArgumentException("The Prescription Has Not Been Deleted");
        }
        logger.info("The Prescription With Prescription ID {} Has Been Deleted Successfully", prescriptionId);
    }
}