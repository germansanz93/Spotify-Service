package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.Album;
import com.pinapp.spotifyservice.domain.mappers.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

  @Qualifier("albums")
  @Autowired
  private List<Album> albums;

  @Autowired
  private AlbumMapper albumMapper;

  public List<Album> getAlbums(){
    return albums;
  }

  public Album getAlbum(Long id){
    Album album = albums.stream().filter(a -> a.getIdAlbum() == id)
        .findFirst().orElse(null);
    return album;
  }

  public Album createAlbum(AlbumRequest request){
    Album album =  albumMapper.apply(request);
    return album;
  }

  public Album updateAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    return album;
  }

  public Album deleteAlbum(Long id){
    Album album = albums.stream().filter(a -> a.getIdAlbum() == id)
        .findFirst().orElse(null);
    return album;
  }
}
