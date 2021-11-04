package com.pinapp.spotifyservice.domain.mappers;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.Album;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AlbumMapper implements Function<AlbumRequest, Album> {

  public Album apply(AlbumRequest albumRequest){
    return Album.builder()
        .idAlbum(albumRequest.getIdAlbum())
        .idArtist(albumRequest.getIdArtist())
        .name(albumRequest.getName())
        .build();
  }
}
