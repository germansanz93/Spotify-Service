package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.Track;
import com.pinapp.spotifyservice.domain.mappers.TrackMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class TrackService {

  @Autowired
  public TrackMapper trackMapper;


  private final List<Track> tracks = new ArrayList<>();

  public List<Track> getTracks(){
    log.info("getTracks request");
    return tracks;
  }

  public Track getTrack(Long id){
    log.info(String.format("getTrackById request with id: %d", id));
    return tracks.stream().filter(track -> Objects.equals(track.getId(), id)).findFirst().orElse(null);
  }

  public Track createTrack(TrackRequest request){
    Track track =  trackMapper.apply(request);
    Long id = 0L;
    if(tracks.size() > 0) id = tracks.get(tracks.size() - 1).getId() + 1L;
    track.setId(id);
    track.setReproductions(0L);
    tracks.add(track);
    log.info(String.format("createTrack request, created with id: %d", id));
    return track;
  }

  public Track updateTrack(TrackRequest request){
    Track track = trackMapper.apply(request);
    final Long idTrack = track.getId();
    if(tracks.stream().filter(a -> Objects.equals(a.getId(), idTrack)).findFirst().orElse(null) != null){
      tracks.set(track.getId().intValue(), track);
    }else {
      track = null;
    }
    log.info(String.format("updateTrack request, updated with id: %d", idTrack));
    return track;
  }

  public Track deleteTrack(Long id){
    Track track = tracks.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst().orElse(null);
    tracks.remove(track);
    log.info(String.format("deleteTrack request, deleted with id: %d", id));
    return track;
  }

  public Track playTrack(Long idTrack){
    Track track = null;
    if(tracks.stream().filter(a -> Objects.equals(a.getId(), idTrack)).findFirst().orElse(null) != null){
      track = this.getTrack(idTrack);
      track.setReproductions(track.getReproductions() + 1);
      tracks.set(track.getId().intValue(), track);
    }
    return track;
  }
}
