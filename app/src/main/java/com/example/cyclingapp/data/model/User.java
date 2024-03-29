package com.example.cyclingapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey @NonNull
    public String id;

    @ColumnInfo(name = "role")
    public Role role;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
    
    @ColumnInfo(name = "password_salt")
    public String passwordSalt;
    
    @ColumnInfo(name = "password_hash")
    public String passwordHash;

    @NonNull
    public String getId() {
        return id;
    }
}