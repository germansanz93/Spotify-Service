package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.Album;
import com.pinapp.spotifyservice.domain.mappers.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

  @Autowired
  private AlbumMapper albumMapper;

  private List<Album> albums = new ArrayList<Album>();

  public List<Album> getAlbums(){
    return albums;
  }

  public Album getAlbum(Long id){
    return albums.stream().filter(a -> a.getIdAlbum() == id)
        .findFirst().orElse(null);
  }

  public Album createAlbum(AlbumRequest request){
    Album album =  albumMapper.apply(request);
    Long id = 0L;
    if(albums.size() > 0) id = albums.get(albums.size() - 1).getIdAlbum() + 1L;
    album.setIdAlbum(id);
    albums.add(album);
    return album;
  }

  public Album updateAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    final Long idAlbum = album.getIdAlbum();
    Optional<Album> foundAlbum = albums.stream().filter(a -> a.getIdAlbum() == idAlbum).findFirst();
    if(foundAlbum.isPresent()){
      albums.set(Long.valueOf(album.getIdAlbum()).intValue(), album);
    }else {
      album = null;
    }
    return album;

  }

  public Album deleteAlbum(Long id){
    Album album = albums.stream().filter(a -> a.getIdAlbum() == id)
        .findFirst().orElse(null);
    albums.remove(album);
    return album;
  }
}
