package fr.cyu.jee.model;

import jakarta.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "")
public class Student extends User implements Serializable {

    @Column(name = "", updatable = false, nullable = false, length=50)
    private List<Course> courses;
    public Student(String lastName, String firstName, String contact, String identification, String cryptedPassword, Date dateOfBirth) {
        super(lastName, firstName, contact, identification, cryptedPassword, dateOfBirth, Permissions.STUDENT);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
