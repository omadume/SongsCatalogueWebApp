package com.example.demo.controllers;

import com.example.demo.repositories.CatalogueRepository;
import com.example.demo.models.Catalogue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalogues")
public class CatalogueController {

    private final CatalogueRepository catalogueRepository;

    public CatalogueController(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @GetMapping("/{id}")
    public Catalogue getCatalogue(@PathVariable Long id) {
        return catalogueRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Catalogue Id. Catalogue could not be found."));
    }
}