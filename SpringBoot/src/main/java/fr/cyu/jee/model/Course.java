package fr.cyu.jee.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, updatable = false, nullable = false, length=50)
    private Long identification;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public Course(Long identification, List<Student> students, Teacher teacher, Subject subject) {
        this.identification = identification;
        this.students = students;
        this.teacher = teacher;
        this.subject = subject;
    }

    public Course(){}

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
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
