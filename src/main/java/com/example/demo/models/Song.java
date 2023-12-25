package com.example.demo.models;

import com.example.demo.models.Catalogue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    private String songName;
    private String artistName;
    private String albumName;
    private Integer releaseYear;
    private Integer durationSecs;
    private String genre;

    private String songDetails;

    @ManyToOne
    @JoinColumn(name="catalogue_id", nullable=true)
    private Catalogue catalogue;

    public Song() {
    }

    public Song(String songName, String artistName, String albumName, Integer releaseYear, Integer durationSecs, String genre, String songDetails, Catalogue catalogue) {
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.releaseYear = releaseYear;
        this.durationSecs = durationSecs;
        this.genre = genre;
        this.songDetails = songDetails;
        this.catalogue = catalogue;
    }

    public Long getId() {
        return id;
    }


    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(Integer durationSecs) {
        this.durationSecs = durationSecs;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSongDetails(String songDetails) {
        this.songDetails = songDetails;
    }
    public String getSongDetails() {
        return songDetails;
    }


    @JsonBackReference
    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }



}