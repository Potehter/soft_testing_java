package ru.mantis.model;


import org.hibernate.annotations.Type;
import org.hibernate.type.IntegerType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserData {
    @Id
    private int id;
    private String username;
    private String email;
    private short access_level;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public short getAccess_level() {
        return access_level;
    }
}
