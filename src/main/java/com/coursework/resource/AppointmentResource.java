/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.AppointmentDAO;
import com.coursework.exception.ResourceNotFoundException;
import com.coursework.model.Appointment;
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

@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentResource.class);
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    public List<Appointment> getAllAppointments() {
        logger.info("Fetching All The Appointments");
        return appointmentDAO.getAllAppointments();
    }

    @GET
    @Path("/{appointmentId}")
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        logger.info("Fetching The Appointment By The Id Number: {}", appointmentId);
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            return Response.ok(appointment).build();
        } catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addAppointment(Appointment appointment) {
        logger.info("The Appointment Has Been Added: {}", appointment);
        appointmentDAO.addAppointment(appointment);
        return Response.status(Response.Status.CREATED).entity(appointment).build();
    }

    @PUT
    @Path("/{appointmentId}")
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        logger.info("The Appointment Has Been Updated: {}: {}", appointmentId, updatedAppointment);
        try {
            Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);
            if (existingAppointment != null) {
                existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
                existingAppointment.setDoctorName(updatedAppointment.getDoctorName());
                existingAppointment.setPatientName(updatedAppointment.getPatientName());
                existingAppointment.setAppointmentReason(updatedAppointment.getAppointmentReason());

                appointmentDAO.updateAppointment(existingAppointment);
                return Response.ok(existingAppointment).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Appointment With The Id " + appointmentId + " Was Not Found.")
                        .build();
            }
        } catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        logger.info("The Appointment With The Id {} Has Been Deleted Successfully", appointmentId);
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            return Response.noContent().build();
        } catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
