package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.domain.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

  @Qualifier("artists")
  @Autowired
  private List<Artist> artists;

  public List<Artist> getArtists(){
    return artists;
  }

}
