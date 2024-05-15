/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.MedicalRecordDAO;
import com.coursework.exception.ResourceNotFoundException;
import com.coursework.model.MedicalRecord;
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

@Path("/medicalrecords")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordResource.class);
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @GET
    public List<MedicalRecord> getAllRecords() {
        try {
            return medicalRecordDAO.getAllRecords();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving records", e);
        }
    }

    @GET
    @Path("/{patientId}")
    public Response getRecordByPatientId(@PathParam("patientId") int patientId) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getRecordByPatientId(patientId);
            return Response.ok(medicalRecord).build();
        } catch (ResourceNotFoundException e) {
            logger.error("Medical record for patient ID {} not found", patientId);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Error retrieving medical record for patient ID {}: {}", patientId, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addRecord(MedicalRecord record) {
        try {
            medicalRecordDAO.addRecord(record);
            return Response.status(Response.Status.CREATED).entity(record).build();
        } catch (Exception e) {
            logger.error("Error adding medical record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{patientId}")
    public Response updateRecord(MedicalRecord updatedRecord, @PathParam("patientId") int patientId) {
        try {
            medicalRecordDAO.updateRecord(updatedRecord);
            return Response.ok().build();
        } catch (ResourceNotFoundException e) {
            logger.error("Medical record for patient ID {} not found", patientId);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Error updating medical record for patient ID {}: {}", patientId, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{patientId}")
    public Response deleteRecord(@PathParam("patientId") int patientId) {
        try {
            medicalRecordDAO.deleteRecord(patientId);
            return Response.noContent().build();
        } catch (ResourceNotFoundException e) {
            logger.error("Record for patient ID {} not found", patientId);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Error deleting record for patient ID {}: {}", patientId, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}