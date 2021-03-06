package com.pinapp.spotifyservice.domain.mapper;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {
  public Artist apply(ArtistRequest artistRequest) {
    return Artist.builder()
        .idArtist(artistRequest.getIdArtist())
        .name(artistRequest.getName())
        .genre(artistRequest.getGenre())
        .image(artistRequest.getImage())
        .build();
  }
}
