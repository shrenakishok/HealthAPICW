/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.resource;

import com.coursework.dao.PersonDAO;
import com.coursework.exception.UserNotFoundException;
import com.coursework.model.Person;
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

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private static final Logger logger = LoggerFactory.getLogger(PersonResource.class);

    private PersonDAO personDAO;

    public PersonResource() {

        this.personDAO = new PersonDAO();
    }

    @GET
    public List<Person> getAllPeople() {
        logger.info("Fetching All The People");
        return personDAO.getAllPeople();
    }

    @GET
    @Path("/{personId}")
    public Response getPersonById(@PathParam("personId") int personId) {
        logger.info("Fetching The Person By The Id: {}", personId);
        try {
            Person person = personDAO.getPersonById(personId);
            return Response.ok(person).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addPerson(Person person) {
        logger.info("The Person Has Been Added: {}", person);
        personDAO.addPerson(person);
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @PUT
    @Path("/{personId}")
    public Response updatePerson(@PathParam("personId") int personId, Person updatedPerson) {
        logger.info("The Person Has Been Updated: {}: {}", personId, updatedPerson);
        try {
            Person existingPerson = personDAO.getPersonById(personId);
            if (existingPerson != null) {
                // Assuming you have appropriate setter methods in your Person class
                existingPerson.setPersonName(updatedPerson.getPersonName());
                existingPerson.setPersonAge(updatedPerson.getPersonAge());
                existingPerson.setPersonContact(updatedPerson.getPersonContact());
                existingPerson.setPersonAddress(updatedPerson.getPersonAddress());

                personDAO.updatePerson(existingPerson);
                return Response.ok(existingPerson).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Person With The Id " + personId + " Was Not Found.")
                        .build();
            }
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{personId}")
    public Response deletePerson(@PathParam("personId") int personId) {
        logger.info("The Person With The Id {} Has Been Deleted Successfully", personId);
        try {
            personDAO.deletePerson(personId);
            return Response.noContent().build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
