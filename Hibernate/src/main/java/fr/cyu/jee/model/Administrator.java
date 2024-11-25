package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@Entity
public class Administrator extends User implements Serializable {
    public Administrator(String lastName, String firstName, String contact, String identification, String cryptedPassword, Date dateOfBirth) {
        super(lastName, firstName, contact, identification, cryptedPassword, dateOfBirth);
    }

    public Administrator(){ super(); }
}