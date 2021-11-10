package com.pinapp.spotifyservice.domain.mapper;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.model.Track;
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
        .artist(artistRepository.findById(trackRequest.getIdArtist()).get())
        .album(albumRepository.findById(trackRequest.getIdAlbum()).get())
        .duration(trackRequest.getDuration())
        .build();
  }
}
