package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.Track;
import com.pinapp.spotifyservice.domain.mappers.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

  @Autowired
  public TrackMapper trackMapper;

  @Qualifier("tracks")
  @Autowired
  private List<Track> tracks;

  public List<Track> getTracks(){ return tracks; };

  public Track getTrack(Long id){
    return tracks.stream().filter(track -> track.getId() == id).findFirst().orElse(null);
  };

  public Track createTrack(TrackRequest request){
    Track track = trackMapper.apply(request);
    return track;
  }

  public Track updateTrack(TrackRequest request){
    Track track = trackMapper.apply(request);
    return track;
  }
  public Track deleteTrack(Long id){
    Track track = tracks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    return track;
  }
}
