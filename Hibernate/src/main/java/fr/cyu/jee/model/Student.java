package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Student extends User implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courses;
    public Student(String lastName, String firstName, String contact, String identification, String cryptedPassword, Date dateOfBirth) {
        super(lastName, firstName, contact, identification, cryptedPassword, dateOfBirth);
    }

    public Student(){ super(); }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
