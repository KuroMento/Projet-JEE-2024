package fr.cyu.jee.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The available actions according to your status. Used to load actions on the web page with the jsp file.
 */
public enum Permissions {
    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    ADMIN("ADMIN");

    private final String permission;

    Permissions(String permission){
        this.permission = permission;
    }

    @Override
    public String toString(){
        return this.permission;
    }
}
