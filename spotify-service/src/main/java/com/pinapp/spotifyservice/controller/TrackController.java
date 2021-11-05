package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.Track;
import com.pinapp.spotifyservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/track")
public class TrackController {

  @Autowired
  private TrackService trackService;

  @GetMapping
  public List<Track> retrieveTrack(){return trackService.getTracks();}

  @GetMapping(path="/{id}")
  public Track retrieveTrack(@PathVariable Long id){
    return trackService.getTrack(id);
  }

  @PostMapping
  public Track createTrack(@Validated @RequestBody TrackRequest request){
    return trackService.createTrack(request);
  }

  @PutMapping(path="/{id}")
  public Track updateTrack(@PathVariable Long id, @RequestBody TrackRequest request){
    request.setId(id);
    return trackService.updateTrack(request);
  }

  @DeleteMapping(path="/{id}")
  public Track deleteTrack(@PathVariable Long id){
    return trackService.deleteTrack(id);
  }
}
