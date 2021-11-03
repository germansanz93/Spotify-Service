package com.pinapp.spotifyservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/track")
public class TrackController {
  @GetMapping(path = "/")
  public String retrieveTrack(){
    return "Hello from track";
  }
}
