/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoctorDAO {

    private static final Logger logger = LoggerFactory.getLogger(DoctorDAO.class);
    private static List<Doctor> doctors = new ArrayList<>();

    static {
        Doctor doctorMary = new Doctor(1, 3, "Mary", 37, "0766681920", "02, Oakfield Place", "Cosmetologist");
        doctors.add(doctorMary);

        Doctor doctorChris = new Doctor(2, 4, "Chris", 32, "0721967481", "01, 67th Lane, Mainland", "Cardiologist");
        doctors.add(doctorChris);
    }

    public List<Doctor> getAllDoctors() {
        logger.info("Fetching All The Doctors From The Array List");
        return doctors;
    }

    public Doctor getDoctorById(int Id) {
        logger.info("Fetching The Doctor By The Identification Number {}", Id);
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId()== Id) {
                logger.info("The Doctor With The Identification Number {} Has Been Found", Id);
                return doctor;
            }
        }
        logger.info("The Doctor With The Identification Number {} Has Not Been Found", Id);
        return null;
    }

    public void addDoctor(Doctor doctor) {
        if (doctor == null || doctor.getPersonName() == null || doctor.getPersonName().isEmpty()) {
            throw new IllegalArgumentException("There's An Error In Adding The Doctor To The Array List");
        }
        doctors.add(doctor);
        logger.info("The Doctor Has Been Added: {}", doctor);
    }

    public void updateDoctor(Doctor updatedDoctor) {
        if (updatedDoctor == null) {
            throw new IllegalArgumentException("There's An Error In Updating The Doctor Within The Array List");
        }
        int id = updatedDoctor.getDoctorId();
        boolean found = false;
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getDoctorId() == id) {
                doctors.set(i, updatedDoctor);
                found = true;
                logger.info("The Doctor Has Been Updated: {}", updatedDoctor);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The Doctor With The Identification Number Has Not Been Found");
        }
    }

    public void deleteDoctor(int id) {
        boolean removed = doctors.removeIf(doctor -> doctor.getDoctorId() == id);
        if (!removed) {
            throw new IllegalArgumentException("The Doctor Has Not Been Deleted From The Array List");
        }
        logger.info("The Doctor With The Identification Number {} Has Been Deleted Successfully", id);
    }

}
