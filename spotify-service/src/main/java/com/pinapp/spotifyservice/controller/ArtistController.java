package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.service.Implementation.ArtistService;
import com.pinapp.spotifyservice.service.Implementation.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/artist")
public class ArtistController {

  @Autowired
  private ArtistService artistService;

  @Autowired
  private TrackService trackService;

  @GetMapping
  public List<Artist> retrieveArtist(){return artistService.getArtists();}

  @GetMapping(path = "/{id}")
  public Artist retrieveArtist(@PathVariable Long id){return artistService.getArtist(id);}

  @GetMapping(path = "/{id}/songs/rank")
  public List<Track> retrieveArtistRankedSongs(@PathVariable Long id, @RequestParam(name = "limit") Optional<Integer> limit){
    return trackService.getArtistRankedTracks(id, limit.orElse(5));
  }

  @GetMapping(path = "/rank")
  public List<Artist> retrieveArtistsRanked(@RequestParam(name = "limit") Optional<Integer> limit){return artistService.getTopArtists(limit.orElse(5));}

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Artist createArtist(@RequestBody ArtistRequest artist){return artistService.createArtist(artist);}

  @PutMapping(path = "/{id}")
  public Artist updateArtist(@PathVariable Long id, @RequestBody ArtistRequest request){
    request.setIdArtist(id);
    return artistService.updateArtist(request);
  }

  @DeleteMapping(path = "/{id}")
  public Artist deleteArtist(@PathVariable Long id){return artistService.deleteArtist(id);}
}
