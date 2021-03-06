package com.pinapp.spotifyservice.domain.mapper;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.repository.IArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AlbumMapper implements Function<AlbumRequest, Album> {

  @Autowired
  private IArtistRepository artistRepository;

  public Album apply(AlbumRequest albumRequest) {
    return Album.builder()
        .idAlbum(albumRequest.getIdAlbum())
        .artist(artistRepository.findById(albumRequest.getIdArtist())
            .orElseThrow(() -> new ArtistNotExistException(String.format("Artist with id %d doesn't exist", albumRequest.getIdArtist()))))
        .name(albumRequest.getName())
        .build();
  }
}
