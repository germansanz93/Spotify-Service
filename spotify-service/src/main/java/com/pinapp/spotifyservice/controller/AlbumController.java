package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.repository.AlbumRepository;
import com.pinapp.spotifyservice.service.Implementation.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/album")
public class AlbumController {

  @Autowired
  private AlbumRepository albumRepository;

  @Autowired
  private AlbumService albumService;

  @GetMapping
  public List<Album> retrieveAlbums(){
    return albumService.getAlbums();
  }

  @GetMapping(path = "/{id}")
  public Album retrieveAlbum(@PathVariable Long id){ return albumService.getAlbum(id);};

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Album createAlbum(@Validated @RequestBody AlbumRequest request){
    return albumService.createAlbum(request);
  }

  @PutMapping(path = "/{id}")
  public Album updateAlbum( @PathVariable Long id, @Validated @RequestBody AlbumRequest request ){
    request.setIdAlbum(id);
    return albumService.updateAlbum(request);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteAlbum( @PathVariable Long id ){albumService.deleteAlbum(id);}
}
