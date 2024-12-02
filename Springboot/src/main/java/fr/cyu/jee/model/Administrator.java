package fr.cyu.jee.model;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
public class Administrator extends User implements Serializable {

    public Administrator(){ super(); }
}