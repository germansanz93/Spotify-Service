package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.mapper.AlbumMapper;
import com.pinapp.spotifyservice.exception.AlbumExistException;
import com.pinapp.spotifyservice.exception.AlbumNotExistException;
import com.pinapp.spotifyservice.repository.AlbumRepository;
import com.pinapp.spotifyservice.service.IAlbumService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.annotations.Nullability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.lang.model.type.NullType;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class AlbumService implements IAlbumService {

  @Autowired
  private AlbumRepository albumRepository;

  @Autowired
  private AlbumMapper albumMapper;

  @Autowired
  @Qualifier("albums")
  private List<Album> albums;

  @PostConstruct
  public void init() {
    albums.forEach(album -> albumRepository.save(album));
  }

  public List<Album> getAlbums(){
    log.info("getAlbums request");
    return StreamSupport.stream(albumRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  public Album getAlbum(Long id){
    Optional<Album> foundAlbum = albumRepository.findById(id);
    if(foundAlbum.isPresent()) log.info(String.format("getAlbumById request with id: %d", id));
    else {
      log.error(String.format("Album with id: %d doesn't exist", id));
      throw new AlbumNotExistException("Album doesn't exist");
    }
    return foundAlbum.get();
  }

  public Album createAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    Album savedAlbum;
    Long id = album.getIdAlbum();
    if(id != null && albumRepository.findById(id).isPresent()){
      log.error(String.format("the id %d is already taken", id));
      throw new AlbumExistException(String.format("the id %d is already taken", id));
    } else{
      savedAlbum = albumRepository.save(album);
      log.info("createAlbum request, created");
    }
    return savedAlbum;
  }

  public Album updateAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    Album updatedAlbum;
    Long id = album.getIdAlbum();
    if(id != null && albumRepository.findById(id).isPresent()){
      updatedAlbum =  albumRepository.save(album);
    }else {
      log.error("The album doesn't exist");
      throw new AlbumNotExistException("The album doesn't exist!");
    }
    log.info(String.format("updateAlbum request, updated with id: %d", updatedAlbum.getIdAlbum()));
    return updatedAlbum;

  }

  public Album deleteAlbum(Long id){
    Optional<Album> albumToDelete = albumRepository.findById(id);
    if(albumToDelete.isPresent()){
      albumRepository.deleteById(id);
      log.info(String.format("deleteAlbum request, deleted with id: %d", id));
    } else {
      log.error("The album doesn't exist");
      throw new AlbumNotExistException("The album doesn't exist!");
    }
    return albumToDelete.get();
  }
}
