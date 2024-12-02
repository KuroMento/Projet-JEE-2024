package fr.cyu.jee.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * The class representing a composed / foreign key in Grade.
 */
public class GradeId implements Serializable {
    @Column(name = "studentId", updatable = false, nullable = false, length=50)
    private int studentId;
    @Column(name = "courseId", updatable = false, nullable = false, length=50)
    private int courseId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return courseId;
    }

    public void setClassId(int classId) {
        this.courseId = classId;
    }
}
