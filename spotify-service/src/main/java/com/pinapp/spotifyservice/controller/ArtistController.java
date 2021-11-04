package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/artists")
public class ArtistController {

  @Autowired
  private ArtistService artistService;

  @GetMapping(path = "/")
  public List<Artist> retrieveArtist(){return artistService.getArtists();}
}
