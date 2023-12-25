package com.example.demo.controllers;

import com.example.demo.repositories.SongRepository;
import com.example.demo.models.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) throws Exception {
        if(songRepository.existsBySongNameAndArtistNameAndCatalogueId(song.getSongName(), song.getArtistName(), song.getCatalogue().getId())){
            throw new RuntimeException("Song already exists.");
        }
        try {
            Song createdSong = songRepository.save(song);
            return ResponseEntity.created(new URI("/songs/" + createdSong.getId())).body(createdSong);
        } catch (RuntimeException | URISyntaxException e) {
            throw new Exception("Song could not be created.");
        }
    }

}