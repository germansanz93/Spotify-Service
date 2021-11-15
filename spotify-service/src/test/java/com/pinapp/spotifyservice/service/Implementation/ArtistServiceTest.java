package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.mapper.ArtistMapper;
import com.pinapp.spotifyservice.domain.mapper.ArtistRankedMapper;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.ArtistRanked;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.exception.ArtistExistException;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.repository.IAlbumRepository;
import com.pinapp.spotifyservice.repository.IArtistRepository;
import com.pinapp.spotifyservice.repository.ITrackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

  @InjectMocks
  @Spy
  public ArtistService artistService;

  @Mock
  public IArtistRepository artistRepository;

  @Mock
  public ITrackRepository trackRepository;

  @Mock
  public IAlbumRepository albumRepository;

  @Mock
  public ArtistMapper artistMapper;

  @Mock
  public TrackService trackService;

  @Mock
  public ArtistRankedMapper artistRankedMapper;

  List<Artist> fakeArtistList = Arrays.asList(
      Artist.builder()
          .idArtist(1L)
          .name("Heroes del silencio")
          .genre("Rock")
          .image("https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/" +
              "1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg")
          .build(),

      Artist.builder()
          .idArtist(2L)
          .name("Metallica")
          .genre("Heavy metal")
          .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
          .build(),

      Artist.builder()
          .idArtist(3L)
          .name("Stevie Ray Vaughan")
          .genre("Blues")
          .image("https://i1.wp.com/jessicakristie.com/wp-content/uploads/2012/02/StevieRayVaughan.jpg")
          .build()
  );

  Artist fakeArtist = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

  Artist fakeArtist2 = Artist.builder()
      .idArtist(2L)
      .name("Metallica")
      .genre("Heavy metal")
      .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
      .build();

  Album fakeAlbum = Album.builder().idAlbum(2L).artist(fakeArtist2).name("Ride the lightning").build();

  List<Track> fakeTrackList = Arrays.asList(
      Track.builder()
          .id(2L)
          .name("For whom the bell tolls")
          .artist(fakeArtist2)
          .album(fakeAlbum)
          .duration(306000L)
          .reproductions(3213L)
          .build(),
      Track.builder()
          .id(3L)
          .name("...And justice for all")
          .artist(fakeArtist2)
          .album(fakeAlbum)
          .duration(567600L)
          .reproductions(5413L)
          .build()
  );

  List<ArtistRanked> fakeMappedArtist = Arrays.asList(
      ArtistRanked.builder()
          .idArtist(1L)
          .name("Heroes del silencio")
          .genre("Rock")
          .image("https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/" +
              "1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg")
          .reproductions(1123L)
          .build(),

      ArtistRanked.builder()
          .idArtist(2L)
          .name("Metallica")
          .genre("Heavy metal")
          .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
          .reproductions(8626L)
          .build(),

      ArtistRanked.builder()
          .idArtist(3L)
          .name("Stevie Ray Vaughan")
          .genre("Blues")
          .image("https://i1.wp.com/jessicakristie.com/wp-content/uploads/2012/02/StevieRayVaughan.jpg")
          .reproductions(3413L)
          .build()
  );

  List<ArtistRanked> fakeSortedArtists = Arrays.asList(
      ArtistRanked.builder()
          .idArtist(2L)
          .name("Metallica")
          .genre("Heavy metal")
          .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
          .reproductions(8626L)
          .build(),
      ArtistRanked.builder()
          .idArtist(3L)
          .name("Stevie Ray Vaughan")
          .genre("Blues")
          .image("https://i1.wp.com/jessicakristie.com/wp-content/uploads/2012/02/StevieRayVaughan.jpg")
          .reproductions(3413L)
          .build(),
      ArtistRanked.builder()
          .idArtist(1L)
          .name("Heroes del silencio")
          .genre("Rock")
          .image("https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/" +
              "1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg")
          .reproductions(1123L)
          .build()
  );

  ArtistRequest artistRequest = ArtistRequest.builder().name("La renga").genre("Rock").image("ImageURL").build();
  Artist fakeArtistInput = Artist.builder().name("La renga").genre("Rock").image("ImageURL").build();
  Artist fakeArtistResponse = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

  @Test
  public void getArtistsSuccess() {

    when(artistRepository.findAll()).thenReturn(fakeArtistList);

    assertEquals(fakeArtistList, artistService.getArtists());

  }

  @Test
  public void getArtistSuccess() {

    when(artistRepository.findById(fakeArtist.getIdArtist())).thenReturn(Optional.ofNullable(fakeArtist));

    assertEquals(artistService.getArtist(fakeArtist.getIdArtist()), fakeArtist);
  }

  @Test
  public void getArtistFailByIdNotExists() {
    when(artistRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(ArtistNotExistException.class, () -> artistService.getArtist(1L));
  }

  @Test
  public void artistReproductionsSuccess() {

    Long fakeReproductions = 3213L + 5413;

    Long idArtist = 1L;

    when(trackService.getTracksByArtist(idArtist)).thenReturn(fakeTrackList);

    assertEquals(fakeReproductions, artistService.artistReproductions(idArtist));

  }

  @Test
  public void getTopArtistsSuccess() {

    when(artistRepository.findAll()).thenReturn(fakeArtistList);
    when(artistRankedMapper.apply(fakeArtistList.get(0))).thenReturn(fakeMappedArtist.get(0));
    when(artistRankedMapper.apply(fakeArtistList.get(1))).thenReturn(fakeMappedArtist.get(1));
    when(artistRankedMapper.apply(fakeArtistList.get(2))).thenReturn(fakeMappedArtist.get(2));
    doReturn(fakeMappedArtist.get(0).getReproductions()).when(artistService).artistReproductions(fakeMappedArtist.get(0).getIdArtist());
    doReturn(fakeMappedArtist.get(1).getReproductions()).when(artistService).artistReproductions(fakeMappedArtist.get(1).getIdArtist());
    doReturn(fakeMappedArtist.get(2).getReproductions()).when(artistService).artistReproductions(fakeMappedArtist.get(2).getIdArtist());

    assertEquals(fakeSortedArtists, artistService.getTopArtists(2));

  }

  @Test
  public void createArtistSuccess() {

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtistInput);
    when(artistRepository.save(eq(fakeArtistInput))).thenReturn(fakeArtistResponse);

    Artist artistResponse = artistService.createArtist(artistRequest);

    assertEquals(fakeArtistResponse, artistResponse);
  }

  @Test
  public void createArtistFailByExistId() {

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtist);
    when(artistRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(java.util.Optional.of(fakeArtist));

    assertThrows(ArtistExistException.class, () -> artistService.createArtist(artistRequest));
  }

  @Test
  public void updateArtistSuccess() {

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtist);
    when(artistRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(java.util.Optional.of(fakeArtist));
    when(artistRepository.save(eq(fakeArtist))).thenReturn(fakeArtist);

    Artist artistResponse = artistService.updateArtist(artistRequest);

    assertEquals(fakeArtist, artistResponse);
  }

  @Test
  public void updateArtistFailByNotExistingId() {

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtist);
    when(artistRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(Optional.empty());

    assertThrows(ArtistNotExistException.class, () -> artistService.updateArtist(artistRequest));
  }

  @Test
  public void deleteArtistSuccess() {

    when(artistRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(java.util.Optional.of(fakeArtist));

    artistService.deleteArtist(fakeArtist.getIdArtist());

    verify(artistRepository, times(1)).findById(eq(fakeArtist.getIdArtist()));
    verify(trackRepository, times(1)).deleteByIdArtist(eq(1L));
    verify(albumRepository, times(1)).deleteByIdArtist(eq(1L));
    verify(artistRepository, times(1)).deleteById(eq(1L));

  }
}