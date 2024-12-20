package fr.cyu.jee.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * User represents a person that can be a Teacher, a Student or an Administrator
 */
@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    /**
     * The last name of the user
     */
    @Column(name = "lastName", nullable = false, length=50)
    private String lastName;
    /**
     * The first name of the user
     */
    @Column(name = "firstName", nullable = false, length=50)
    private String firstName;
    /**
     * The email address
     */
    @Column(name = "contact", nullable = false, length=50)
    private String contact;
    /**
     * The unique identifier
     */
    @Id
    @Column(name = "id", unique = true, updatable = false,  nullable = false, length=50)
    private String identification;
    /**
     * The encrypted password
     */
    @Column(name = "encryptedPassword", nullable = false, length=50)
    private String cryptedPassword;
    /**
     * The date of birth
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfBirth", nullable = false, length=50)
    private Date dateOfBirth;
    /**
     * The permissions of the user
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "rights", nullable = false, length=50)
    private Permissions permissions;

    public User() {
    }

    /**
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the new last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the email address (or the contact)
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the email address
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the unique identifier
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * @param identification the unique identifier
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * @return the encrypted password
     */
    public String getCryptedPassword() {
        return cryptedPassword;
    }

    /**
     * @param cryptedPassword the encrypted password
     */
    public void setCryptedPassword(String cryptedPassword) {
        this.cryptedPassword = cryptedPassword;
    }

    /**
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the list of permissions defining a user
     */
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the new list of permissions defining a user
     */
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }
}
