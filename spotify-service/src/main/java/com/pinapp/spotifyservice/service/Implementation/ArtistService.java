package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.mapper.ArtistMapper;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.repository.ArtistRepository;
import com.pinapp.spotifyservice.service.IArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ArtistService implements IArtistService {

  @Autowired
  public ArtistRepository artistRepository;

  @Autowired
  public TrackService trackService;

  @Autowired
  public ArtistMapper artistMapper;

  @Autowired
  @Qualifier("artists")
  private List<Artist> artists;

  @PostConstruct
  public void init() {
    artists.stream().forEach(artist -> artistRepository.save(artist));
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

  public Long artistReproductions(Long id){
    return trackService.getTracksByArtist(id).stream().mapToLong(Track::getReproductions).sum();
  }

  public void updateArtistReproductions(Long idArtist){
    Artist artist = getArtist(idArtist);
    artist.setReproductions(artistReproductions(idArtist));
    log.info(artistReproductions(idArtist).toString());
    artistsList.set(idArtist.intValue()-1, artist);
  }

  public List<Artist> getTopArtists(int limit){
    return artistsList.stream().sorted(Comparator.comparingDouble(Artist::getReproductions).reversed()).limit(limit).collect(Collectors.toList());
  }

  public Artist createArtist(ArtistRequest request){
    Artist artist =  artistMapper.apply(request);
    Long id = 1L;
    if(artistsList.size() > 0) id = artistsList.get(artistsList.size() - 1).getIdArtist() + 1L;
    artist.setIdArtist(id);
    artist.setReproductions(artistReproductions(id));
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
      log.info("artist not found");
      throw new ArtistNotExistException("The track doesn't exist");
    }
    log.info(String.format("updateArtist request, updated with id: %d", idArtist));
    return artist;
  }

  public Artist deleteArtist(Long id){
    Optional<Artist> artist = artistsList.stream().filter(a -> Objects.equals(a.getIdArtist(), id)).findFirst();
    log.info(String.format("deleteArtist request, deleted with id: %d", id));
    if(artist.isPresent()){
      artistsList.remove(artist.get());
    }
    else {
      log.info("artist not found, nothing will be deleted");
      throw new ArtistNotExistException("The track doesn't exist");
    }
    return artist.get();
  }

}
