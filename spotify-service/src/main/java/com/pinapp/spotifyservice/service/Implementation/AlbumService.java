package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.mapper.AlbumMapper;
import com.pinapp.spotifyservice.exception.AlbumNotExistException;
import com.pinapp.spotifyservice.service.IAlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AlbumService implements IAlbumService {

  @Autowired
  private AlbumMapper albumMapper;


  @Autowired
  @Qualifier("albums")
  private List<Album> albums;

  @PostConstruct
  public void init() {
    albumsList = new ArrayList<>();
    albumsList.addAll(albums);
  }

  private List<Album> albumsList;


  public List<Album> getAlbums(){
    log.info("getAlbums request");
    return albumsList;
  }

  public Album getAlbum(Long id){
    log.info(String.format("getAlbumById request with id: %d", id));
    return albumsList.stream().filter(a -> Objects.equals(a.getIdAlbum(), id))
        .findFirst().orElse(null);
  }

  public Album createAlbum(AlbumRequest request){
    Album album =  albumMapper.apply(request);
    Long id = 1L;
    if(albumsList.size() > 0) id = albumsList.get(albumsList.size() - 1).getIdAlbum() + 1L;
    album.setIdAlbum(id);
    albumsList.add(album);
    log.info(String.format("createAlbum request, created with id: %d", id));
    return album;
  }

  public Album updateAlbum(AlbumRequest request){
    Album album = albumMapper.apply(request);
    final Long idAlbum = album.getIdAlbum();
    Optional<Album> foundAlbum = albumsList.stream().filter(a -> Objects.equals(a.getIdAlbum(), idAlbum)).findFirst(); //TODO implement this as a method
    if(foundAlbum.isPresent()){
      albumsList.set(album.getIdAlbum().intValue()-1, album);
    }else {
      log.error("The album doesn't exist");
      throw new AlbumNotExistException("The album doesn't exist!");
    }
    log.info(String.format("updateAlbum request, updated with id: %d", idAlbum));
    return album;

  }

  public Album deleteAlbum(Long id){
    Optional<Album> album = albumsList.stream().filter(a -> Objects.equals(a.getIdAlbum(), id)).findFirst();
    if(album.isPresent()){ albumsList.remove(album.get());
      log.info(String.format("deleteAlbum request, deleted with id: %d", id));
    }else {
      log.error("The album doesn't exist");
      throw new AlbumNotExistException("The album doesn't exist!");
    }
    return album.get();
  }
}
