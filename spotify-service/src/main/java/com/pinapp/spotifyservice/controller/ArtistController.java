package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.Artist;
import com.pinapp.spotifyservice.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/artists")
public class ArtistController {

  @Autowired
  private ArtistService artistService;

  @GetMapping(path = "/")
  public List<Artist> retrieveArtist(){return artistService.getArtists();}

  @GetMapping(path = "/{id}")
  public Artist retrieveArtist(@PathVariable Long id){return artistService.getArtist(id);}

  @PostMapping(path = "/")
  public Artist createAlbum(@RequestBody ArtistRequest artist){return artistService.createArtist(artist);}

  @PutMapping(path = "/{id}")
  public Artist updateAlbum(@PathVariable Long id, @RequestBody ArtistRequest request){
    request.setIdArtist(id);
    return artistService.createArtist(request);
  }

  @DeleteMapping(path = "/{id}")
  public Artist deleteArtist(@PathVariable Long id){return artistService.deleteArtist(id);}
}
