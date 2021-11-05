package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.domain.mappers.ArtistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

  @Autowired
  public ArtistMapper artistMapper;


  private List<Artist> artists = new ArrayList<Artist>();

  public List<Artist> getArtists(){
    return artists;
  }

  public Artist getArtist(Long id){
    return artists.stream().filter(a -> a.getIdArtist() == id)
        .findFirst().orElse(null);
  }

  public Artist createArtist(ArtistRequest request){
    Artist artist =  artistMapper.apply(request);
    Long id = 0L;
    if(artists.size() > 0) id = artists.get(artists.size() - 1).getIdArtist() + 1L;
    artist.setIdArtist(id);
    artists.add(artist);
    return artist;
  }

  public Artist updateArtist(ArtistRequest request){
    Artist artist = artistMapper.apply(request);
    final Long idArtist = artist.getIdArtist();
    Optional<Artist> foundArtist = artists.stream().filter(a -> a.getIdArtist() == idArtist).findFirst();

    if (foundArtist.isPresent()){
      artists.set(Long.valueOf(artist.getIdArtist()).intValue(), artist);
    } else {
      artist = null;
    }

    return artist;
  }

  public Artist deleteArtist(Long id){
    Artist artist = artists.stream().filter(a -> a.getIdArtist() == id)
        .findFirst().orElse(null);
    artists.remove(artist);
    return artist;
  }

}
