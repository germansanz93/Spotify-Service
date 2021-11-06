package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.domain.Track;
import com.pinapp.spotifyservice.service.Implementations.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/spotify")
public class SpotifyController {

  @Autowired
  SpotifyService spotifyService;

  @GetMapping(path = "/play/track/{id}")
  public Track retrieveTrack(@PathVariable Long id){return spotifyService.playTrack(id);}

}
