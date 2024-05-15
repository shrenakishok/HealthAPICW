/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.PrescriptionDAO;
import com.coursework.model.Prescription;
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

@Path("/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {

    private static final Logger logger = LoggerFactory.getLogger(PrescriptionResource.class);
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @GET
    public Response getAllPrescriptions() {
        logger.info("Fetching All The Prescriptions");
        return Response.ok(prescriptionDAO.getAllPrescriptions()).build();
    }

    @GET
    @Path("/{prescriptionId}")
    public Response getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        logger.info("Fetching The Prescription By ID: {}", prescriptionId);
        Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
        if (prescription != null) {
            return Response.ok(prescription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prescription With ID " + prescriptionId + " Was Not Found.")
                    .build();
        }
    }

    @POST
    public Response addPrescription(Prescription prescription) {
        logger.info("The Prescription Has Been Added: {}", prescription);
        prescriptionDAO.addPrescription(prescription);
        return Response.status(Response.Status.CREATED).entity(prescription).build();
    }

    @PUT
    @Path("/{prescriptionId}")
    public Response updatePrescription(@PathParam("prescriptionId") int prescriptionId, Prescription updatedPrescription) {
        logger.info("The Prescription Has Been Updated: {}: {}", prescriptionId, updatedPrescription);
        Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);
        if (existingPrescription != null) {
            prescriptionDAO.updatePrescription(updatedPrescription);
            return Response.ok(updatedPrescription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prescription With ID " + prescriptionId + " Was Not Found.")
                    .build();
        }
    }

    @DELETE
    @Path("/{prescriptionId}")
    public Response deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        logger.info("The Prescription With ID {} Has Been Deleted Successfully", prescriptionId);
        Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
        if (prescription != null) {
            prescriptionDAO.deletePrescription(prescriptionId);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prescription With ID " + prescriptionId + " Was Not Found.")
                    .build();
        }
    }
}