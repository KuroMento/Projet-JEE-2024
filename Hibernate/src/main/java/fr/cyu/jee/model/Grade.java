package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Grades")
public class Grade implements Serializable {
    @EmbeddedId
    private GradeId id;
    @Column(name = "grade_value", updatable = false, nullable = false, length=50)
    private double value;
    @Column(name = "grade_coefficient", updatable = false, nullable = false, length=50)
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

    public Grade(GradeId id, double value, double coefficient) {
        this.id = id;
        this.value = value;
        this.coefficient = coefficient;
    }

    public Grade(){
    }
}
