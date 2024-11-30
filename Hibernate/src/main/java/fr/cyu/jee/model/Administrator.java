package fr.cyu.jee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@Entity
public class Administrator extends User implements Serializable {

    public Administrator(){ super(); }
}