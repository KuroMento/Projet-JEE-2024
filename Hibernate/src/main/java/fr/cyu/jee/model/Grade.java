package fr.cyu.jee.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "")
public class Grade implements Serializable {
    @EmbeddedId
    @Column(name = "", unique = true, updatable = false, nullable = false, length=50)
    private GradeId id;
    @Column(name = "", updatable = false, nullable = false, length=50)
    private double value;
    @Column(name = "", updatable = false, nullable = false, length=50)
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
