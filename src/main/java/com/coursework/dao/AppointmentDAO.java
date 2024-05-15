/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.model.Appointment;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppointmentDAO {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentDAO.class);
    private static List<Appointment> appointments = new ArrayList<>();
    
      static {
        Appointment appointment1 = new Appointment(1, "19th Monday 2023", "Michelle", "James", "Routine Checkup");
        appointments.add(appointment1);
    }

    public List<Appointment> getAllAppointments() {
        logger.info("Fetching All The Appointments From the Array List");
        return appointments;
    }

    public Appointment getAppointmentById(int identificationNumber) {
        logger.info("Fetching The Appointment By The Identification Number {}", identificationNumber);
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == identificationNumber) {
                logger.info("The Appointment With The Identification Number {} Has Been Found", identificationNumber);
                return appointment;
            }
        }
        logger.info("The Appointment With The Identification Number {} Has Not Been Found", identificationNumber);
        return null;
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("There's An Error In Adding The Appointment To The Array List");
        }
        appointments.add(appointment);
        logger.info("The Appointment Has Been Added: {}", appointment);
    }

    public void updateAppointment(Appointment updatedAppointment) {
        if (updatedAppointment == null) {
            throw new IllegalArgumentException("There's An Error In Updating The Appointment Within The Array List");
        }
        int identificationNumber = updatedAppointment.getAppointmentId();
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getAppointmentId() == identificationNumber) {
                appointments.set(i, updatedAppointment);
                found = true;
                logger.info("The Appointment Has Been Updated: {}", updatedAppointment);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The Appointment With The Identification Number Has Not Been Found");
        }
    }

    public void deleteAppointment(int identificationNumber) {
        boolean removed = appointments.removeIf(appointment -> appointment.getAppointmentId() == identificationNumber);
        if (!removed) {
            throw new IllegalArgumentException("The Appointment Has Not Been Deleted From The Array List");
        }
        logger.info("The Appointment With The Identification Number {} Has Been Deleted Successfully", identificationNumber);
    }
    
}
