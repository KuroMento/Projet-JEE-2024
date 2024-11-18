package fr.cyu.jee.model;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "")
public class Course{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "", unique = true, updatable = false, nullable = false, length=50)
    private int identification;
    @ManyToMany
    private List<Student> students;
    @ManyToOne
    @JoinColumn(name = "teacher", nullable = false)
    private Teacher teacher;

    private Subject subject;

    public Course(int identification, List<Student> students, Teacher teacher, Subject subject) {
        this.identification = identification;
        this.students = students;
        this.teacher = teacher;
        this.subject = subject;
    }

    public Course(){}

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
