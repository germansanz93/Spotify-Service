package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.domain.mapper.TrackMapper;
import com.pinapp.spotifyservice.service.ITrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrackService implements ITrackService {

  @Autowired
  public TrackMapper trackMapper;

  @Autowired
  @Qualifier("tracks")
  private List<Track> tracks;

  @PostConstruct
  public void init() {
    trackList = new ArrayList<>();
    trackList.addAll(tracks);
  }

  private List<Track> trackList;

  public List<Track> getTracks(){
    log.info("getTracks request");
    return trackList;
  }

  public List<Track> getArtistRankedTracks(Long idArtist){
    log.info(String.format("getTracksByArtist request with idArtist: %d", idArtist));
    return trackList
        .stream()
        .filter(track -> Objects.equals(track.getIdArtist(), idArtist))
        .sorted(Comparator.comparing(Track::getReproductions).reversed())
        .collect(Collectors.toList());
  }

  public List<Track> getArtistRankedTracks(Long idArtist,int limit){
    log.info(String.format("and limit: %d", limit));
    return getArtistRankedTracks(idArtist).subList(0, limit);
  }

  public Track getTrack(Long id){
    log.info(String.format("getTrackById request with id: %d", id));
    return trackList.stream().filter(track -> Objects.equals(track.getId(), id)).findFirst().orElse(null);
  }

  public Track createTrack(TrackRequest request){
    Track track =  trackMapper.apply(request);
    Long id = 1L;
    if(trackList.size() > 0) id = trackList.get(trackList.size() - 1).getId() + 1L;
    track.setId(id);
    track.setReproductions(0L);
    trackList.add(track);
    log.info(String.format("createTrack request, created with id: %d", id));
    return track;
  }

  public Track updateTrack(TrackRequest request){
    Track track = trackMapper.apply(request);
    final Long idTrack = track.getId();
    Optional<Track> foundTrack = trackList.stream().filter(a -> Objects.equals(a.getId(), idTrack)).findFirst();

    if(foundTrack.isPresent()){
      trackList.set(Math.toIntExact(idTrack)-1 , track);
    }else {
      track = null;
    }
    log.info(String.format("updateTrack request, updated with id: %d", idTrack));
    return track;
  }

  public Track deleteTrack(Long id){
    Optional<Track> track = trackList.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst();
    track.ifPresent(value -> trackList.remove(value));
    log.info(String.format("deleteTrack request, deleted with id: %d", id));
    return track.get();
  }

  public Track playTrack(Long idTrack){
    Optional<Track> track = trackList.stream().filter(t -> Objects.equals(t.getId(), idTrack)).findFirst();
    if(track.isPresent()){
      track.get().setReproductions(track.get().getReproductions() + 1);
      trackList.set(track.get().getId().intValue(), track.get());
    }
    return track.get();
  }
}
