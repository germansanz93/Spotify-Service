package com.pinapp.spotifyservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController {
  @GetMapping(path = "/")
  public String retrieveArtist(){
    return "Hello From Artist Controller";
  }
}
