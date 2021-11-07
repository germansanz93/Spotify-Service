package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.domain.mapper.TrackMapper;
import com.pinapp.spotifyservice.exception.TrackNotExistException;
import com.pinapp.spotifyservice.repository.TrackRepository;
import com.pinapp.spotifyservice.service.ITrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrackService implements ITrackService {

  @Autowired
  public TrackRepository trackRepository;

  @Autowired
  public ArtistService artistService;

  @Autowired
  public TrackMapper trackMapper;

  @Autowired
  @Qualifier("tracks")
  private List<Track> tracks;

  @PostConstruct
  public void init() {
    tracks.stream().forEach(track -> trackRepository.save(track));
  }

  private List<Track> trackList;

  public List<Track> getTracks(){
    log.info("getTracks request");
    return trackList;
  }

  public List<Track> getTracksByArtist(Long idArtist){
    log.info(String.format("getTracksByArtist request with idArtist: %d", idArtist));
    return trackList
        .stream()
        .filter(track -> Objects.equals(track.getIdArtist(), idArtist))
        .collect(Collectors.toList());
  }

  public List<Track> getArtistRankedTracks(Long idArtist){
    log.info(String.format("getTracksByArtist request with idArtist: %d but ranked", idArtist));
    return getTracksByArtist(idArtist)
        .stream()
        .sorted(Comparator.comparing(Track::getReproductions).reversed())
        .collect(Collectors.toList());
  }

  public List<Track> getArtistRankedTracks(Long idArtist,int limit){
    log.info(String.format("getTracksByArtist request with idArtist: %d but ranked and limited %d", idArtist, limit));
    return getArtistRankedTracks(idArtist).subList(0, limit);
  }

  public List<Track> getRankedTracks(int limit){
    return trackList.stream().sorted(Comparator.comparing(Track::getReproductions).reversed()).limit(limit).collect(Collectors.toList());
  }

  public Track getTrack(Long id){
    Optional<Track> foundTrack = trackList.stream().filter(track -> Objects.equals(track.getId(), id)).findFirst();
    if(foundTrack.isPresent()){
      log.info(String.format("getTrackById request with id: %d", id));
    }else{
      log.error("The track doesn't exist");
      throw new TrackNotExistException("The track doesn't exist");
    }
    return foundTrack.get();
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
    Long idTrack = track.getId();
    Optional<Track> foundTrack = trackList.stream().filter(a -> Objects.equals(a.getId(), idTrack)).findFirst();
    if(foundTrack.isPresent()){
      track.setReproductions(foundTrack.get().getReproductions());
      trackList.set(Math.toIntExact(idTrack)-1 , track);
    }else {
      log.error("The track doesn't exist");
      throw new TrackNotExistException("The track doesn't exist");
    }
    log.info(String.format("updateTrack request, updated with id: %d", idTrack));
    return track;
  }

  public Track deleteTrack(Long id){
    Optional<Track> track = trackList.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst();
    if(track.isPresent()){
      trackList.remove(track);
    } else{
      log.error("The track doesn't exist");
      throw new TrackNotExistException("The track doesn't exist");
    }
    log.info(String.format("deleteTrack request, deleted with id: %d", id));
    return track.get();
  }

  public Track playTrack(Long idTrack){
    Optional<Track> track = trackList.stream().filter(t -> Objects.equals(t.getId(), idTrack)).findFirst();
    if(track.isPresent()){
      track.get().setReproductions(track.get().getReproductions() + 1);
      trackList.set(track.get().getId().intValue()-1, track.get());
      artistService.updateArtistReproductions(track.get().getIdArtist());
    } else {
      log.error("The track doesn't exist");
      throw new TrackNotExistException("The track doesn't exist");
    }
    return track.get();
  }
}
