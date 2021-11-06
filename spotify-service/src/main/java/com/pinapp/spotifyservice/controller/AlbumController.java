package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.Album;
import com.pinapp.spotifyservice.service.Implementations.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/album")
public class AlbumController {

  @Autowired
  private AlbumService albumService;

  @GetMapping
  public List<Album> retrieveAlbums(){
    return albumService.getAlbums();
  }

  @GetMapping(path = "/{id}")
  public Album retrieveAlbum(@PathVariable Long id){ return albumService.getAlbum(id);};

  @PostMapping
  public Album createAlbum(@Validated @RequestBody AlbumRequest request){
    return albumService.createAlbum(request);
  }

  @PutMapping(path = "/{id}")
  public Album updateAlbum( @PathVariable Long id, @Validated @RequestBody AlbumRequest request ){
    request.setIdAlbum(id);
    return albumService.updateAlbum(request);
  }

  @DeleteMapping(path = "/{id}")
  public Album deleteAlbum( @PathVariable Long id ){
    return albumService.deleteAlbum(id);
  }
}
