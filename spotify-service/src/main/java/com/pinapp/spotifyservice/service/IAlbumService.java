package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;

import java.util.List;

public interface IAlbumService {

  List<Album> getAlbums();

  Album getAlbum(Long id);

  Album createAlbum(AlbumRequest request);

  Album updateAlbum(AlbumRequest request);

  void deleteAlbum(Long id);
}
