package fr.cyu.jee.model;

import java.io.Serializable;

/**
 * The class representing a composed / foreign key in Grade.
 */
public class GradeId implements Serializable {
    private int studentId;
    private int classId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
