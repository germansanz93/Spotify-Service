package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.service.Implementations.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController {

  @Autowired
  private ArtistService artistService;

  @GetMapping
  public List<Artist> retrieveArtist(){return artistService.getArtists();}

  @GetMapping(path = "/{id}")
  public Artist retrieveArtist(@PathVariable Long id){return artistService.getArtist(id);}

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
