package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.Album;
import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.domain.Track;
import com.pinapp.spotifyservice.domain.mappers.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {

  @Autowired
  public TrackMapper trackMapper;

//  @Qualifier("tracks")
//  @Autowired
//  private List<Track> tracks;

  private List<Track> tracks = new ArrayList<Track>();

  public List<Track> getTracks(){ return tracks; };

  public Track getTrack(Long id){
    return tracks.stream().filter(track -> track.getId() == id).findFirst().orElse(null);
  };

  public Track createTrack(TrackRequest request){
    Track track =  trackMapper.apply(request);
    Long id = 0L;
    if(tracks.size() > 0) id = tracks.get(tracks.size() - 1).getId() + 1L;
    track.setId(id);
    track.setReproductions(0L);
    tracks.add(track);
    return track;
  }

  public Track updateTrack(TrackRequest request){
    Track track = trackMapper.apply(request);
    final Long idTrack = track.getId();
    if(tracks.stream().filter(a -> a.getId() == idTrack).findFirst().orElse(null) != null){
      tracks.set(Long.valueOf(track.getId()).intValue(), track);
    }else {
      track = null;
    }
    return track;
  }

  public Track deleteTrack(Long id){
    Track track = tracks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    tracks.remove(track);
    return track;
  }

  public Track playTrack(Long idTrack){
    Track track = null;
    if(tracks.stream().filter(a -> a.getId() == idTrack).findFirst().orElse(null) != null){
      track = this.getTrack(idTrack);
      track.setReproductions(track.getReproductions() + 1);
      tracks.set(Long.valueOf(track.getId()).intValue(), track);
    }
    return track;
  }
}
