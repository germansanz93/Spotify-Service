package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.Album;
import com.pinapp.spotifyservice.domain.mappers.AlbumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AlbumService {

  @Autowired
  private AlbumMapper albumMapper;

  private final List<Album> albums = new ArrayList<>();

  public List<Album> getAlbums(){
    log.info("getAlbums request");
    return albums;
  }

  public Album getAlbum(Long id){
    log.info(String.format("getAlbumById request with id: %d", id));
    return albums.stream().filter(a -> Objects.equals(a.getIdAlbum(), id))
        .findFirst().orElse(null);
  }

  public Album createAlbum(AlbumRequest request){
    Album album =  albumMapper.apply(request);
    Long id = 0L;
    if(albums.size() > 0) id = albums.get(albums.size() - 1).getIdAlbum() + 1L;
    album.setIdAlbum(id);
    albums.add(album);
    log.info(String.format("createAlbum request, created with id: %d", id));
    return album;
  }

  public Album updateAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    final Long idAlbum = album.getIdAlbum();
    Optional<Album> foundAlbum = albums.stream().filter(a -> Objects.equals(a.getIdAlbum(), idAlbum)).findFirst(); //TODO implement this as a method
    if(foundAlbum.isPresent()){
      albums.set(album.getIdAlbum().intValue(), album);
    }else {
      album = null;
    }
    log.info(String.format("updateAlbum request, updated with id: %d", idAlbum));
    return album;

  }

  public Album deleteAlbum(Long id){
    Album album = albums.stream().filter(a -> Objects.equals(a.getIdAlbum(), id))
        .findFirst().orElse(null);
    albums.remove(album);
    log.info(String.format("deleteAlbum request, deleted with id: %d", id));

    return album;
  }
}
