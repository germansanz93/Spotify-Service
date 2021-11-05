package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.domain.Track;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SpotifyService {

  @Autowired
  private TrackService trackService;
  public Track playTrack(Long id){
   log.info("playTrack request with id: %d", id);
    return trackService.playTrack(id);
 }
}
