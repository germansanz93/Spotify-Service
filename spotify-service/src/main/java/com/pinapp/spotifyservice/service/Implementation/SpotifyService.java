package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.service.ISpotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SpotifyService implements ISpotifyService {

  @Autowired
  private TrackService trackService;

  public Track playTrack(Long id){

    log.info(String.format("playTrack request with id: %d", id));

    return trackService.playTrack(id);
  }

}
