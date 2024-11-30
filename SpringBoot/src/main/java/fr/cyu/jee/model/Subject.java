package fr.cyu.jee.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The class that represents different subjects and their labels
 */

@Entity
@Table(name = "Subjects")
public class Subject implements Serializable {
    /**
     * The name of the class
     */
    @Basic
    @Column(name = "label", nullable = true, length = 30)
    private String label;
    /**
     * A short description of the class content
     */
    @Column(name = "descr", nullable = false, length=50)
    private String description;
    /**
     * A number to identify each class
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, updatable = false, nullable = false, length=50)
    private Long identification;
    /**
     * A number indicating the prevalence of the class within a module.
     */
    @Basic
    @Column(name = "subject_coefficient", nullable = false, precision = 0)
    private double coefficient;

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private List<Course> courses;

    public Subject(String label, String description, Long identification, double coefficient, List<Course> courses){
        this.label = label;
        this.description = description;
        this.identification = identification;
        this.coefficient = coefficient;
        this.courses = courses;
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
    public Long getIdentification(){
        return identification;
    }

    /**
     *
     * @param identification the identifier for the subject
     */
    public void setIdentification(Long identification) {
        this.identification = identification;
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

    /**
     * @return subject's available courses.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * @param courses subject's available courses.
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
