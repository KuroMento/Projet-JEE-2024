package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Grades")
public class Grade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false, nullable = false, length=50)
    private Long identification;

    @Column(name = "student_id", nullable = false, length=50)
    private String studentId;
    @Column(name = "course_id", nullable = false, length=50)
    private Long courseId;
    @Column(name = "grade_value", nullable = false, length=50)
    private double value;
    @Column(name = "grade_coefficient", nullable = false, length=50)
    private double coefficient;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public Grade(){
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getCourse() {
        return courseId;
    }

    public void setCourse(Long courseId) {
        this.courseId = courseId;
    }
}
