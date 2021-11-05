package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService {

  @Autowired
  private TrackService trackService;
  public Track playTrack(Long id){
   return trackService.playTrack(id);
 }
}
