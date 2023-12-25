package com.example.demo.controllers;

import com.example.demo.LoginOrRegisterRequest;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.Catalogue;
import com.example.demo.models.Song;
import com.example.demo.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid User Id. User could not be found."));
    }

    @RequestMapping(params = "user_name")
    public User getUserByUsername(@RequestParam(value="user_name") String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Invalid Username. User could not be found."));
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginOrRegisterRequest loginOrRegisterRequest) {
        return userRepository.findByUsernameAndPassword(loginOrRegisterRequest.getUsername(), loginOrRegisterRequest.getPassword()).orElseThrow(() -> new RuntimeException("Invalid Username or Password. User could not be found."));
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody LoginOrRegisterRequest loginOrRegisterRequest) {
        if(userRepository.existsByUsername(loginOrRegisterRequest.getUsername())){
            throw new RuntimeException("Username already exists.");
        }
        try {
            User createdUser = new User(loginOrRegisterRequest.getUsername(), loginOrRegisterRequest.getPassword());
            createdUser.setCatalogue(new Catalogue(createdUser));
            return userRepository.save(createdUser);
        } catch (RuntimeException e) {
            throw new RuntimeException("User could not be registered.");
        }
    }

    @RequestMapping(value = "/catalogue", params = "user_id")
    public Long getUserCatalogueId(@RequestParam("user_id") Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid User Id. User could not be found."));
        return existingUser.getCatalogue().getId();
    }

    @RequestMapping(value = "/catalogue/songs", params = "user_id")
    public Set<Song> getUserSongsFromCatalogue(@RequestParam("user_id") Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid User Id. User could not be found."));
        return existingUser.getCatalogue().getSongs();
    }

    @RequestMapping(value = "/catalogue/songs", params = {"user_id", "release_year"})
    public List<Song> filterSongsByReleaseYear(@RequestParam("user_id") Long id, @RequestParam("release_year") Integer year) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid User Id. User could not be found."));
        return existingUser.getCatalogue().getSongsByReleaseYear(year);
    }

    @RequestMapping(value = "/catalogue/songs", params = {"user_id", "artist_name"})
    public List<Song> filterSongsByArtistName(@RequestParam("user_id") Long id, @RequestParam("artist_name") String name) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid User Id. User could not be found."));
        return existingUser.getCatalogue().getSongsByArtistName(name);
    }

}