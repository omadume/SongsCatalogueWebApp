package com.example.demo.models;

import com.example.demo.models.Catalogue;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users") // Note: using 'users' instead of 'User' as the former is a reserved SQL keyword
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 120)
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    private Catalogue catalogue;

    public User() {
    }

    public User(String username, String password, Catalogue catalogue) {
        this.username = username;
        this.password = password;
        this.catalogue = catalogue;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonManagedReference
    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }
}