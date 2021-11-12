package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.service.Implementation.AlbumService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlbumControllerTest {

  @InjectMocks
  public AlbumController albumController;

  @Mock
  public AlbumService albumService;

  @Test
  public void retrieveAlbums() {
  }

  @Test
  public void retrieveAlbum() {
  }

  @Test
  public void CreateAlbumSuccess() {
    AlbumRequest albumRequest = AlbumRequest.builder().idAlbum(2L).name("La esquina del infinito").idArtist(2L).build();
    Artist fakeArtist = Artist.builder().idArtist(1L).image("image").name("name").build();
    Album fakeAlbum = Album.builder().idAlbum(2L).name("La esquina del infinito").artist(fakeArtist).build();
    when(albumService.createAlbum(eq(albumRequest))).thenReturn(fakeAlbum);
    Album albumResponse = albumController.createAlbum(albumRequest);

    verify(albumService, times(1)).createAlbum(eq(albumRequest));

    Assert.assertEquals("La esquina del infinito", albumResponse.getName());
    Assert.assertEquals(2L, albumResponse.getIdAlbum().longValue());
    Assert.assertEquals(fakeArtist, albumResponse.getArtist());
  }

  @Test
  public void updateAlbum() {
  }

  @Test
  public void deleteAlbum() {
  }
}