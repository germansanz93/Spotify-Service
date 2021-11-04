package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.Album;
import com.pinapp.spotifyservice.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/albums")
public class AlbumController {

  @Autowired
  private AlbumService albumService;

  @GetMapping(path = "/")
  public List<Album> retrieveAlbums(){
    return albumService.getAlbums();
  }

  @GetMapping(path = "/{id}")
  public Album retrieveAlbum(@PathVariable Long id){ return albumService.getAlbum(id);};

  @PostMapping(path = "/")
  public Album createAlbum(@Validated @RequestBody AlbumRequest request){
    return albumService.createAlbum(request);
  }
}
