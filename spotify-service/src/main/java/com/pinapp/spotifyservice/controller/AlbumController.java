package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.domain.Album;
import com.pinapp.spotifyservice.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
