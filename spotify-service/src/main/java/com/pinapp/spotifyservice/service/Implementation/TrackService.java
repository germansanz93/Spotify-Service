package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.domain.mapper.TrackMapper;
import com.pinapp.spotifyservice.exception.AlbumNotExistException;
import com.pinapp.spotifyservice.exception.TrackExistException;
import com.pinapp.spotifyservice.exception.TrackNotExistException;
import com.pinapp.spotifyservice.repository.ITrackRepository;
import com.pinapp.spotifyservice.service.ITrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class TrackService implements ITrackService {

  @Autowired
  public ITrackRepository trackRepository;

  @Autowired
  public ArtistService artistService;

  @Autowired
  public TrackMapper trackMapper;

  public List<Track> getTracks() {
    log.info("getTracks request");
    return StreamSupport.stream(trackRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  public List<Track> getTracksByArtist(Long idArtist) {
    log.info(String.format("getTracksByArtist request with idArtist: %d", idArtist));
    return getTracks()
        .stream()
        .filter(track -> Objects.equals(track.getArtist().getIdArtist(), idArtist))
        .collect(Collectors.toList());
  }

  public List<Track> getArtistRankedTracks(Long idArtist) {
    log.info(String.format("getTracksByArtist request with idArtist: %d but ranked", idArtist));
    return getTracksByArtist(idArtist)
        .stream()
        .sorted(Comparator.comparing(Track::getReproductions).reversed())
        .collect(Collectors.toList());
  }

  public List<Track> getArtistRankedTracks(Long idArtist, int limit) {
    log.info(String.format("getTracksByArtist request with idArtist: %d but ranked and limited %d", idArtist, limit));
    return getArtistRankedTracks(idArtist).subList(0, limit);
  }

  public List<Track> getRankedTracks(int limit) {
    return getTracks().stream().sorted(Comparator.comparing(Track::getReproductions).reversed()).limit(limit).collect(Collectors.toList());
  }

  public Track getTrack(Long id) {
    return trackRepository.findById(id).orElseThrow(() -> new AlbumNotExistException(String.format("Artist with id %d doesn't exist!", id)));
  }

  public Track createTrack(TrackRequest request) {
    Track track = trackMapper.apply(request);
    track.setReproductions(0L);
    Long id = track.getId();
    Track savedTrack;
    if (id != null && trackRepository.findById(id).isPresent()) {
      log.error(String.format("the id %d is already taken", id));
      throw new TrackExistException(String.format("the id %d is already taken", id));
    } else {
      savedTrack = trackRepository.save(track);
      log.info("createTrack request... created");
    }
    return savedTrack;
  }

  public Track updateTrack(TrackRequest request) {
    Track track = trackMapper.apply(request);
    log.info(track.toString());
    Long id = track.getId();
    Track foundTrack = trackRepository.findById(id).orElseThrow(() -> new TrackNotExistException(String.format("Track with id %d doesn't exist!", id)));
    track.setReproductions(foundTrack.getReproductions());
    return trackRepository.save(track);
  }

  public void deleteTrack(Long id) {
    trackRepository.findById(id).orElseThrow(() -> new AlbumNotExistException(String.format("Track with id %d doesn't exist!", id)));
    trackRepository.deleteById(id);
  }

  public Track playTrack(Long idTrack) {
    try {
      Track track = getTrack(idTrack);
      track.setReproductions(track.getReproductions() + 1);
      trackRepository.save(track);
      return track;
    } catch (TrackNotExistException e) {
      log.error("The track doesn't exist");
      throw e;
    }
  }
}