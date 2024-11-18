package fr.cyu.jee.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "")
public class Administrator extends User implements Serializable {
    public Administrator(String lastName, String firstName, String contact, String identification, String cryptedPassword, Date dateOfBirth) {
        super(lastName, firstName, contact, identification, cryptedPassword, dateOfBirth, Permissions.ADMIN);
    }

    public Administrator(){ super(); }
}