/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.DoctorDAO;
import com.coursework.exception.UserNotFoundException;
import com.coursework.model.Doctor;
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

@Path("/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {

    private static final Logger logger = LoggerFactory.getLogger(DoctorResource.class);

    private DoctorDAO doctorDAO;

    public DoctorResource() {
        this.doctorDAO = new DoctorDAO();
    }

    @GET
    public List<Doctor> getAllDoctors() {
        logger.info("Fetching All The Doctors");
        return doctorDAO.getAllDoctors();
    }

    @GET
    @Path("/{doctorId}")
    public Response getDoctorById(@PathParam("doctorId") int doctorId) {
        logger.info("Fetching The Doctor By The Id Number: {}", doctorId);
        try {
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            return Response.ok(doctor).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addDoctor(Doctor doctor) {
        logger.info("The Doctor Has Been Added: {}", doctor);
        doctorDAO.addDoctor(doctor);
        return Response.status(Response.Status.CREATED).entity(doctor).build();
    }

    @PUT
    @Path("/{doctorId}")
    public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
        logger.info("The Doctor Has Been Updated: {}: {}", doctorId, updatedDoctor);
        try {
            Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
            if (existingDoctor != null) {
                // Assuming you have appropriate setter methods in your Doctor class
                existingDoctor.setPersonName(updatedDoctor.getPersonName());
                existingDoctor.setPersonAge(updatedDoctor.getPersonAge());
                existingDoctor.setPersonContact(updatedDoctor.getPersonContact());
                existingDoctor.setPersonAddress(updatedDoctor.getPersonAddress());
                existingDoctor.setDoctorSpecialization(updatedDoctor.getDoctorSpecialization());

                doctorDAO.updateDoctor(existingDoctor);
                return Response.ok(existingDoctor).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Doctor With The Id " + doctorId + " Was Not Found.")
                        .build();
            }
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") int doctorId) {
        logger.info("The Doctor With The Id {} Has Been Deleted Successfully", doctorId);
        try {
            doctorDAO.deleteDoctor(doctorId);
            return Response.noContent().build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
