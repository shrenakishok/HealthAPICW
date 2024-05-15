/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.exception.UserNotFoundException;
import com.coursework.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDAO {

    private static final Logger logger = LoggerFactory.getLogger(PersonDAO.class);
    private static List<Person> people = new ArrayList<>();

    static {
        initializePeopleList();
    }

    private static void initializePeopleList() {
        people.add(new Person(1, "Aaron", 25, "0778897301", "3B, Heights, Laveder Street"));
        people.add(new Person(2, "Jordan", 19, "0764457910", "121, Origami Street"));
        people.add(new Person(3, "Mary", 37, "0766681920", "02, Oakfield Place"));
        people.add(new Person(4, "Chris", 32, "0721967481", "01, 67th Lane, Mainland"));
    }

    public List<Person> getAllPeople() {
        logger.info("Fetching All The People");
        return new ArrayList<>(people);
    }

    public Person getPersonById(int personId) {
        logger.info("Fetching The Person By The Id: {}", personId);
        for (Person person : people) {
            if (person.getPersonId() == personId) {
                logger.info("The Person With The Id {} Has Been Found", personId);
                return person;
            }
        }
        throw new UserNotFoundException("There Is No Person Found With The Id " + personId);
    }

    public void addPerson(Person person) {
        if (person == null || person.getPersonName() == null || person.getPersonName().isEmpty()) {
            throw new IllegalArgumentException("There Was An Error In Adding The Person");
        }
        int newPersonId = getNextId();
        person.setPersonId(newPersonId);
        people.add(person);
        logger.info("The Person Has Been Added: {}", person);
    }

    public void updatePerson(Person updatedPerson) {
        if (updatedPerson == null) {
            throw new IllegalArgumentException("There Was An Error In Updating The Person");
        }
        boolean found = false;
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            if (person.getPersonId() == updatedPerson.getPersonId()) {
                people.set(i, updatedPerson);
                found = true;
                logger.info("The Person Has Been Updated: {}", updatedPerson);
                break;
            }
        }
        if (!found) {
            throw new UserNotFoundException("The Person To Be Updated Was Not Found");
        }
    }

    public void deletePerson(int personId) {
        boolean removed = people.removeIf(person -> person.getPersonId() == personId);
        if (!removed) {
            throw new IllegalArgumentException("The Person Was Not Found To Be Deleted");
        }
        logger.info("The Person With The Id {} Has Been Deleted Successfully", personId);
    }

    private int getNextId() {
        int maximumIdentificationNumber = Integer.MIN_VALUE;
        for (Person person : people) {
            if (person.getPersonId() > maximumIdentificationNumber) {
                maximumIdentificationNumber = person.getPersonId();
            }
        }
        return maximumIdentificationNumber + 1;
    }

}
