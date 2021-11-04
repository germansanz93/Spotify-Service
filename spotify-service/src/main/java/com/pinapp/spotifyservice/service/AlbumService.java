package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.domain.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

  @Qualifier("albums")
  @Autowired
  private List<Album> albums;

  public List<Album> getAlbums(){
    return albums;
  }

}
