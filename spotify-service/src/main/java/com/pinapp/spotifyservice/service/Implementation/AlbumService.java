package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.mapper.AlbumMapper;
import com.pinapp.spotifyservice.exception.AlbumExistException;
import com.pinapp.spotifyservice.exception.AlbumNotExistException;
import com.pinapp.spotifyservice.repository.IAlbumRepository;
import com.pinapp.spotifyservice.repository.ITrackRepository;
import com.pinapp.spotifyservice.service.IAlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class AlbumService implements IAlbumService {

  @Autowired
  private IAlbumRepository albumRepository;

  @Autowired
  private ITrackRepository trackrepository;

  @Autowired
  private AlbumMapper albumMapper;

  public List<Album> getAlbums(){
    log.info("getAlbums request");
    return StreamSupport.stream(albumRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  public Album getAlbum(Long id){
    log.info(String.format("getAlbum request with id: %d", id));
    return albumRepository.findById(id).orElseThrow(() -> new AlbumNotExistException(String.format("Album with id %d doesn't exist!", id)));
  }

  public Album createAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    Long id = album.getIdAlbum();
    Album savedAlbum;
    if(id != null && albumRepository.findById(id).isPresent()){
      log.error(String.format("the id %d is already taken", id));
      throw new AlbumExistException(String.format("the id %d is already taken", id));
    } else{
      savedAlbum = albumRepository.save(album);
      log.info("createAlbum request... created");
    }
    return savedAlbum;
  }

  public Album updateAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    Long id = album.getIdAlbum();
    albumRepository.findById(id).orElseThrow(() -> new AlbumNotExistException(String.format("Album with id %d doesn't exist!", id)));
    return albumRepository.save(album);
  }

  public void deleteAlbum(Long id){
    albumRepository.findById(id).orElseThrow(() -> new AlbumNotExistException(String.format("Album with id %d doesn't exist!", id)));
    trackrepository.deleteByIdAlbum(id);
    albumRepository.deleteById(id);

  }
}
