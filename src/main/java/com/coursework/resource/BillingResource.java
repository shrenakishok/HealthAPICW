/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.BillingDAO;
import com.coursework.model.Billing;
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

@Path("/billing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {

    private static final Logger logger = LoggerFactory.getLogger(BillingResource.class);
    private BillingDAO billingDAO = new BillingDAO();

    @GET
    public List<Billing> getAllBills() {
        logger.info("Fetching All The Billing Records");
        return billingDAO.getAllBills();
    }

    @GET
    @Path("/{billingId")
    public Response getBillbyId(@PathParam("billingId") int billingId) {
        logger.info("The Billing Record Was Found With The Billing Id {}", billingId);
        Billing billing = billingDAO.getBillbyId(billingId);
        if (billing != null) {
            return Response.ok(billing).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("The Billing Record Was Not Found With The Billing Id: " + billingId).build();
        }
    }

    @POST
    public Response addBill(Billing billing) {
        try {
            billingDAO.addBill(billing);
            return Response.status(Response.Status.CREATED).entity(billing).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{billingId}")
    public Response updateBill(@PathParam("billingIde") int billingId, Billing updatedBilling) {
        updatedBilling.setBillingId(billingId);
        try {
            billingDAO.updateBill(updatedBilling);
            return Response.ok(updatedBilling).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{billingId}")
    public Response deleteBill(@PathParam("billingId") int billingId) {
        try {
            billingDAO.deleteBill(billingId);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}