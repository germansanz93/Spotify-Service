package com.pinapp.spotifyservice.domain.mapper;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.exception.AlbumNotExistException;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.repository.IAlbumRepository;
import com.pinapp.spotifyservice.repository.IArtistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Slf4j
public class TrackMapper implements Function<TrackRequest, Track> {

  @Autowired
  private IArtistRepository artistRepository;

  @Autowired
  private IAlbumRepository albumRepository;


  public Track apply(TrackRequest trackRequest) {
    return Track.builder()
        .id(trackRequest.getId())
        .name(trackRequest.getName())
        .artist(artistRepository.findById(trackRequest.getIdArtist())
            .orElseThrow(() -> new ArtistNotExistException(String.format("Artist with id %d doesn't exist", trackRequest.getIdArtist()))))
        .album(albumRepository.findById(trackRequest.getIdAlbum())
            .orElseThrow(() -> new AlbumNotExistException(String.format("Album with id %d doesn't exist", trackRequest.getIdAlbum()))))
        .duration(trackRequest.getDuration())
        .build();
  }
}
