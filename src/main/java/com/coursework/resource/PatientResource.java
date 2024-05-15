/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.PatientDAO;
import com.coursework.exception.UserNotFoundException;
import com.coursework.model.Patient;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    private static final Logger logger = LoggerFactory.getLogger(PatientResource.class);

    private PatientDAO patientDAO;

    public PatientResource() {
        this.patientDAO = new PatientDAO();
    }

    @GET
    public List<Patient> getAllPatients() {
        logger.info("Fetching All The Patients");
        return patientDAO.getAllPatients();
    }

    @GET
    @Path("/{patientId}")
    public Response getPatientById(@PathParam("patientId") int patientId) {
        logger.info("Fetching The Patient By The Id: {}", patientId);
        try {
            Patient patient = patientDAO.getPatientById(patientId);
            return Response.ok(patient).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addPatient(Patient patient) {
        logger.info("The Patient Has Been Added: {}", patient);
        patientDAO.addPatient(patient);
        return Response.status(Response.Status.CREATED).entity(patient).build();
    }

    @PUT
    @Path("/{patientId}")
    public Response updatePatient(@PathParam("patientId") int patientId, Patient updatedPatient) {
        logger.info("The Patient Has Been Updated: {}: {}", patientId, updatedPatient);
        try {
            Patient existingPatient = patientDAO.getPatientById(patientId);
            if (existingPatient != null) {
                // Assuming you have appropriate setter methods in your Patient class
                existingPatient.setPersonName(updatedPatient.getPersonName());
                existingPatient.setPersonAge(updatedPatient.getPersonAge());
                existingPatient.setPersonContact(updatedPatient.getPersonContact());
                existingPatient.setPersonAddress(updatedPatient.getPersonAddress());
                existingPatient.setPatientMedicalHistory(updatedPatient.getPatientMedicalHistory());
                existingPatient.setPatientStatus(updatedPatient.getPatientStatus());

                patientDAO.updatePatient(existingPatient);
                return Response.ok(existingPatient).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Patient With The Identification Number " + patientId + " Was Not Found.")
                        .build();
            }
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") int patientId) {
        logger.info("The Patient With The Id {} Has Been Deleted Successfully", patientId);
        try {
            patientDAO.deletePatient(patientId);
            return Response.noContent().build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
