package fr.cyu.jee.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;
@Entity
public class Administrator extends User implements Serializable {
    public Administrator(String lastName, String firstName, String contact, String identification, String cryptedPassword, Date dateOfBirth) {
        super(lastName, firstName, contact, identification, cryptedPassword, dateOfBirth, Permissions.valueOf("ADMIN"));
    }

    public Administrator(){ super(); }
}