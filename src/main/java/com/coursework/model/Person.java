/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private int personId;
    private String personName;
    private int personAge;
    private String personContact;
    private String personAddress;

    public Person() {
    }

    @JsonCreator
    public Person(@JsonProperty("personId") int personId,
            @JsonProperty("personName") String personName,
            @JsonProperty("personAge") int personAge,
            @JsonProperty("personContact") String personContact,
            @JsonProperty("personAddress") String personAddress) {
        this.personId = personId;
        this.personName = personName;
        this.personAge = personAge;
        this.personContact = personContact;
        this.personAddress = personAddress;
    }

    public int getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    public String getPersonContact() {
        return personContact;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public void setPersonContact(String personContact) {
        this.personContact = personContact;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    @Override
    public String toString() {
        return "Person:{"
                + "Person Id ='" + personId + '\''
                + ", Person Name ='" + personName + '\''
                + ", Person Contact =" + personContact
                + ", Person Address ='" + personAddress + '\''
                + '}';
    }
}
