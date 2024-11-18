package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The class that represents different
 */
@Entity
@Table(name = "")
public class Subject implements Serializable {
    /**
     * The name of the class
     */
    @Column(name = "", updatable = false, nullable = false, length=50)
    private String label;
    /**
     * A short description of the class content
     */
    @Column(name = "", updatable = false, nullable = false, length=50)
    private String description;
    /**
     * A number to identify each class
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "", unique = true, updatable = false, nullable = false, length=50)
    private int identificationNumber;
    /**
     * A number indicating the prevalence of the class within a module.
     */
    @Column(name = "", updatable = false, nullable = false, length=50)
    private double coefficient;
}
