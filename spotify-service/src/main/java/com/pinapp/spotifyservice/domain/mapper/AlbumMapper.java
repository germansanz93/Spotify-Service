package com.pinapp.spotifyservice.domain.mapper;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AlbumMapper implements Function<AlbumRequest, Album> {

  @Autowired
  private ArtistRepository artistRepository;

  public Album apply(AlbumRequest albumRequest){
    return Album.builder()
        .idAlbum(albumRequest.getIdAlbum())
        .artist(artistRepository.findById(albumRequest.getIdArtist()).get())
        .name(albumRequest.getName())
        .build();
  }
}
