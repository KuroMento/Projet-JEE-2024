package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The class that represents different subjects and their labels
 */
@Entity
@Table(name = "Subjects")
public class Subject implements Serializable {
    /**
     * The name of the class
     */
    @Column(name = "label", updatable = false, nullable = false, length=50)
    private String label;
    /**
     * A short description of the class content
     */
    @Column(name = "descr", updatable = false, nullable = false, length=50)
    private String description;
    /**
     * A number to identify each class
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, updatable = false, nullable = false, length=50)
    private int identificationNumber;
    /**
     * A number indicating the prevalence of the class within a module.
     */
    @Column(name = "coefficient", updatable = false, nullable = false, length=50)
    private double coefficient;

    public Subject(String label, String description, int identificationNumber, double coefficient){
        this.label = label;
        this.description = description;
        this.identificationNumber = identificationNumber;
        this.coefficient = coefficient;
    }

    public Subject(){
    }

    /**
     *
     * @return the subject's label
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label the subject's label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return the subject's description
     */
    public String getDescription(){
        return description;
    }

    /**
     *
     * @param description the subject's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the identifier for the subject
     */
    public int getIdentificationNumber(){
        return identificationNumber;
    }

    /**
     *
     * @param identificationNumber the identifier for the subject
     */
    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     *
     * @return subject's coefficient
     */
    public double getCoefficient() {
        return coefficient;
    }

    /**
     *
     * @param coefficient subject's coefficient
     */
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
