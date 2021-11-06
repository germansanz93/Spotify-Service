package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.service.Implementation.ArtistService;
import com.pinapp.spotifyservice.service.Implementation.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public List<Track> retrieveArtistRankedSongs(@PathVariable Long id){return trackService.getArtistRankedTracks(id);}

  @PostMapping
  public Artist createArtist(@RequestBody ArtistRequest artist){return artistService.createArtist(artist);}

  @PutMapping(path = "/{id}")
  public Artist updateArtist(@PathVariable Long id, @RequestBody ArtistRequest request){
    request.setIdArtist(id);
    return artistService.updateArtist(request);
  }

  @DeleteMapping(path = "/{id}")
  public Artist deleteArtist(@PathVariable Long id){return artistService.deleteArtist(id);}
}
