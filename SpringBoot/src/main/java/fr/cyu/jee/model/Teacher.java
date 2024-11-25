package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Teacher extends User implements Serializable {

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;
    public Teacher(String lastName, String firstName, String contact, String identification, String cryptedPassword, Date dateOfBirth) {
        super(lastName, firstName, contact, identification, cryptedPassword, dateOfBirth);
    }
    public Teacher(){ super(); }
    
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
