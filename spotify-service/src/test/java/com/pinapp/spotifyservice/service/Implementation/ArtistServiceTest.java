package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.mapper.ArtistMapper;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.exception.ArtistExistException;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.repository.IArtistRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

  @InjectMocks
  public ArtistService artistService;

  @Mock
  public IArtistRepository artistRepository;

  @Mock
  public ArtistMapper artistMapper;

  @Ignore
  @Test
  public void getArtists() {

  }

  @Ignore
  @Test
  public void getArtist() {
  }

  @Ignore
  @Test
  public void artistReproductions() {
  }

  @Ignore
  @Test
  public void getTopArtists() {
  }

  @Test
  public void createArtistSuccess() {
    ArtistRequest artistRequest = ArtistRequest.builder().name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtistInput = Artist.builder().name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtistResponse = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtistInput);
    when(artistRepository.save(eq(fakeArtistInput))).thenReturn(fakeArtistResponse);

    Artist artistResponse = artistService.createArtist(artistRequest);

    assertEquals(fakeArtistResponse, artistResponse);
  }

  @Test
  public void createArtistFailById() {
    ArtistRequest artistRequest = ArtistRequest.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtistInput = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtistResponse = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtistInput);
    when(artistRepository.findById(eq(fakeArtistInput.getIdArtist()))).thenReturn(java.util.Optional.ofNullable(fakeArtistResponse));

    assertThrows(ArtistExistException.class, () -> artistService.createArtist(artistRequest));
  }

  @Test
  public void updateArtistSuccess() {
    ArtistRequest artistRequest = ArtistRequest.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtistInput = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtistResponse = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtistInput);
    when(artistRepository.findById(eq(fakeArtistInput.getIdArtist()))).thenReturn(java.util.Optional.ofNullable(fakeArtistResponse));
    when(artistRepository.save(eq(fakeArtistResponse))).thenReturn(fakeArtistResponse);

    Artist artistResponse = artistService.updateArtist(artistRequest);

    assertEquals(fakeArtistResponse, artistResponse);
  }

  @Test
  public void updateArtistFailByNotExistingId(){
    ArtistRequest artistRequest = ArtistRequest.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtistInput = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtistInput);
    when(artistRepository.findById(eq(fakeArtistInput.getIdArtist()))).thenReturn(Optional.empty());

    assertThrows(ArtistNotExistException.class, () -> artistService.updateArtist(artistRequest));
  }

  @Ignore
  @Test
  public void deleteArtist() {
  }
}