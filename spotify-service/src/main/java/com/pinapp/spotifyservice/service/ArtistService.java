package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.domain.mappers.ArtistMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ArtistService {

  @Autowired
  public ArtistMapper artistMapper;


  private final List<Artist> artists = new ArrayList<>();

  public List<Artist> getArtists(){
    log.info("getArtists request");
    return artists;
  }

  public Artist getArtist(Long id){
    log.info(String.format("getArtistById request with id: %d", id));
    return artists.stream().filter(a -> Objects.equals(a.getIdArtist(), id))
        .findFirst().orElse(null);
  }

  public Artist createArtist(ArtistRequest request){
    Artist artist =  artistMapper.apply(request);
    Long id = 0L;
    if(artists.size() > 0) id = artists.get(artists.size() - 1).getIdArtist() + 1L;
    artist.setIdArtist(id);
    artists.add(artist);
    log.info(String.format("createArtist request, created with id: %d", id));
    return artist;
  }

  public Artist updateArtist(ArtistRequest request){
    Artist artist = artistMapper.apply(request);
    final Long idArtist = artist.getIdArtist();
    Optional<Artist> foundArtist = artists.stream().filter(a -> Objects.equals(a.getIdArtist(), idArtist)).findFirst();

    if (foundArtist.isPresent()){
      artists.set(artist.getIdArtist().intValue(), artist);
    } else {
      artist = null;
    }
    log.info(String.format("updateArtist request, updated with id: %d", idArtist));
    return artist;
  }

  public Artist deleteArtist(Long id){
    Artist artist = artists.stream().filter(a -> Objects.equals(a.getIdArtist(), id))
        .findFirst().orElse(null);
    artists.remove(artist);
    log.info(String.format("deleteArtist request, deleted with id: %d", id));
    return artist;
  }

}
