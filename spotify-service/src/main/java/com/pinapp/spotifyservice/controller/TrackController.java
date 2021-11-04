package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.domain.Track;
import com.pinapp.spotifyservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tracks")
public class TrackController {

  @Autowired
  private TrackService trackService;

  @GetMapping(path = "/")
  public List<Track> retrieveTrack(){return trackService.getTracks();}
}
