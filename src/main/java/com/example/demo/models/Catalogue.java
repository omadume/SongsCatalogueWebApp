package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "catalogues") // Note: using 'users' instead of 'User' as the former is a reserved SQL keyword
public class Catalogue {

    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy="catalogue", cascade = CascadeType.ALL)
    private Set<Song> songs;

    @OneToOne
    @JoinColumn(name="users_id")
    private User user;

    public Catalogue() {
    }

    public Catalogue(Set<Song> songs, User user) {
        this.songs = songs;
        this.user = user;
    }

    public Catalogue(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }


    @JsonManagedReference
    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Song> getSongsByReleaseYear(Integer year){
        return songs.stream().filter(song -> song.getReleaseYear().equals(year)).toList();
    }

    public List<Song> getSongsByArtistName(String name){
        return songs.stream().filter(song -> song.getArtistName().equals(name)).toList();
    }

}