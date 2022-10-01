package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.mapper.ArtistMapper;
import com.pinapp.spotifyservice.domain.mapper.ArtistRankedMapper;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.ArtistRanked;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.exception.ArtistExistException;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.repository.IAlbumRepository;
import com.pinapp.spotifyservice.repository.IArtistRepository;
import com.pinapp.spotifyservice.repository.ITrackRepository;
import com.pinapp.spotifyservice.service.IArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Service
public class ArtistService implements IArtistService {

  @Autowired
  public ITrackRepository trackRepository;

  @Autowired
  public IArtistRepository artistRepository;

  @Autowired
  public IAlbumRepository albumRepository;

  @Autowired
  public TrackService trackService;

  @Autowired
  public ArtistMapper artistMapper;

  @Autowired
  public ArtistRankedMapper artistRankedMapper;

  public List<Artist> getArtists(){
    log.info("getArtists request");
    return StreamSupport.stream(artistRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  public Artist getArtist(Long id) {
    log.info(String.format("getArtistById request with id: %d", id));

    return artistRepository.findById(id).orElseThrow(() -> new ArtistNotExistException(String.format("Artist with id %d doesn't exist!", id)));
  }

  public Long artistReproductions(Long id) {
    return trackService.getTracksByArtist(id).stream().mapToLong(Track::getReproductions).sum();
  }

  public List<ArtistRanked> getTopArtists(int limit) {
    List<ArtistRanked> rankedArtists = getArtists().stream().map(artist -> artistRankedMapper.apply(artist)).collect(Collectors.toList());
    rankedArtists.forEach(artistRanked -> artistRanked.setReproductions(artistReproductions(artistRanked.getIdArtist())));
    return rankedArtists.stream().sorted(Comparator.comparingLong(ArtistRanked::getReproductions).reversed()).collect(Collectors.toList());
  }

  public Artist createArtist(ArtistRequest request) {
    Artist artist = artistMapper.apply(request);
    Long id = artist.getIdArtist();
    Artist savedArtist;
    if (id != null && artistRepository.findById(id).isPresent()) {
      log.error(String.format("the id %d is already taken", id));
      throw new ArtistExistException(String.format("the id %d is already taken", id));
    } else {
      savedArtist = artistRepository.save(artist);
      log.info("createArtist request... created");
    }
    return savedArtist;
  }

  public Artist updateArtist(ArtistRequest request) {
    Artist artist = artistMapper.apply(request);
    Long id = artist.getIdArtist();
    artistRepository.findById(id).orElseThrow(() -> new ArtistNotExistException(String.format("Artist with id %d doesn't exist!", id)));
    return artistRepository.save(artist);
  }

  public void deleteArtist(Long id) {
    artistRepository.findById(id).orElseThrow(() -> new ArtistNotExistException(String.format("Artist with id %d doesn't exist!", id)));
    trackRepository.deleteTracksByArtist_IdArtist(id);
    albumRepository.deleteAlbumsByArtist_IdArtist(id);
    artistRepository.deleteById(id);
  }
}
