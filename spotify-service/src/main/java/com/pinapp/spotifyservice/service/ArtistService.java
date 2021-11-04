package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.domain.mappers.ArtistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

  @Autowired
  public ArtistMapper artistMapper;

  @Qualifier("artists")
  @Autowired
  private List<Artist> artists;

  public List<Artist> getArtists(){
    return artists;
  }

  public Artist getArtist(Long id){
    Artist artist = artists.stream().filter(a -> a.getIdArtist() == id)
        .findFirst().orElse(null);
    return artist;
  }

  public Artist createArtist(ArtistRequest request){
    Artist artist =  artistMapper.apply(request);
    return artist;
  }

  public Artist updateArtist(ArtistRequest request){
    Artist artist = artistMapper.apply(request);
    return artist;
  }

  public Artist deleteArtist(Long id){
    Artist artist = artists.stream().filter(a -> a.getIdArtist() == id)
        .findFirst().orElse(null);
    return artist;
  }

}
