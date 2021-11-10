package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.service.Implementation.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

  @GetMapping(path = "/rank")
  public List<Track> retrieveTopTracks(@RequestParam(name = "limit") Optional<Integer> limit){return trackService.getRankedTracks(limit.orElse(5));}

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
