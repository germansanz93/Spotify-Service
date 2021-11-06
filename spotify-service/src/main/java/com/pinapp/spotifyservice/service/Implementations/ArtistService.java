package com.pinapp.spotifyservice.service.Implementations;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.domain.mappers.ArtistMapper;
import com.pinapp.spotifyservice.service.IArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ArtistService implements IArtistService {

  @Autowired
  public ArtistMapper artistMapper;

  @Autowired
  @Qualifier("artists")
  private List<Artist> artists;

  @PostConstruct
  public void init() {
    artistsList = new ArrayList<>();
    artistsList.addAll(artists);
  }

  private List<Artist> artistsList;

  public List<Artist> getArtists(){
    log.info("getArtists request");
    return artistsList;
  }

  public Artist getArtist(Long id){
    log.info(String.format("getArtistById request with id: %d", id));
    return artistsList.stream().filter(a -> Objects.equals(a.getIdArtist(), id))
        .findFirst().orElse(null);
  }

  public Artist createArtist(ArtistRequest request){
    Artist artist =  artistMapper.apply(request);
    Long id = 1L;
    if(artistsList.size() > 0) id = artistsList.get(artistsList.size() - 1).getIdArtist() + 1L;
    artist.setIdArtist(id);
    artistsList.add(artist);
    log.info(String.format("createArtist request, created with id: %d", id));
    return artist;
  }

  public Artist updateArtist(ArtistRequest request){
    Artist artist = artistMapper.apply(request);
    final Long idArtist = artist.getIdArtist();
    Optional<Artist> foundArtist = artists.stream().filter(a -> Objects.equals(a.getIdArtist(), idArtist)).findFirst();

    if (foundArtist.isPresent()){
      artistsList.set(artist.getIdArtist().intValue()-1, artist);
    } else {
      artist = null;
    }
    log.info(String.format("updateArtist request, updated with id: %d", idArtist));
    return artist;
  }

  public Artist deleteArtist(Long id){
    Optional<Artist> artist = artistsList.stream().filter(a -> Objects.equals(a.getIdArtist(), id)).findFirst();
    artistsList.remove(artist.get());
    log.info(String.format("deleteArtist request, deleted with id: %d", id));
    return artist.get();
  }

}
