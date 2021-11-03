package com.pinapp.spotifyservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/album")
public class AlbumController {
  @GetMapping(path = "/")
  public String retrieveAlbum(){
    return "Hello from Album";
  }
}
