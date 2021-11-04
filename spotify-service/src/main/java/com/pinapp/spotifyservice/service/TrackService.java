package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

  @Qualifier("tracks")
  @Autowired
  private List<Track> tracks;

  public List<Track> getTracks(){ return tracks; };
}
