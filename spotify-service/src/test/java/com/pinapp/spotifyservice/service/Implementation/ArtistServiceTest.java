package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.mapper.ArtistMapper;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.ArtistRanked;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.exception.ArtistExistException;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.repository.IArtistRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
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

  @Mock
  public TrackService trackService;

  @Test
  public void getArtistsSuccess() {
    List<Artist> fakeRepoResponse = Arrays.asList(
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

    when(artistRepository.findAll()).thenReturn(fakeRepoResponse);

    assertEquals(fakeRepoResponse, artistService.getArtists());

  }

  @Test
  public void getArtistSuccess() {
    Artist fakeArtist = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Long idArtist = 1L;

    when(artistRepository.findById(idArtist)).thenReturn(Optional.ofNullable(fakeArtist));

    assertEquals(artistService.getArtist(idArtist), fakeArtist);
  }

  @Test
  public void getArtistFailByIdNotExist() {
    Long idArtist = 1L;

    when(artistRepository.findById(idArtist)).thenReturn(Optional.empty());

    assertThrows(ArtistNotExistException.class, () -> artistService.getArtist(idArtist));
  }

  @Test
  public void artistReproductionsSuccess() {
    Artist fakeArtist = Artist.builder()
        .idArtist(2L)
        .name("Metallica")
        .genre("Heavy metal")
        .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
        .build();

    Album fakeAlbum =  Album.builder().idAlbum(2L).artist(fakeArtist).name("Ride the lightning").build();

    List<Track> fakeRepoResponse =  Arrays.asList(
        Track.builder()
            .id(2L)
            .name("For whom the bell tolls")
            .artist(fakeArtist)
            .album(fakeAlbum)
            .duration(306000L)
            .reproductions(3213L)
            .build(),
        Track.builder()
            .id(3L)
            .name("...And justice for all")
            .artist(fakeArtist)
            .album(fakeAlbum)
            .duration(567600L)
            .reproductions(5413L)
            .build()
    );

    Long fakeReproductions = 3213L + 5413;

    Long idArtist = 1L;

    when(trackService.getTracksByArtist(idArtist)).thenReturn(fakeRepoResponse);

    assertEquals(fakeReproductions, artistService.artistReproductions(idArtist));

  }

  @Ignore
  @Test
  public void getTopArtistsSuccess() {
    Long idArtist1 = 1L;
    Long idArtist2 = 2L;
    Long idArtist3 = 3L;
    List<Artist> fakeRepoResponse = Arrays.asList(
        Artist.builder()
            .idArtist(idArtist1)
            .name("Heroes del silencio")
            .genre("Rock")
            .image("https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/" +
                "1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg")
            .build(),

        Artist.builder()
            .idArtist(idArtist2)
            .name("Metallica")
            .genre("Heavy metal")
            .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
            .build(),

        Artist.builder()
            .idArtist(idArtist3)
            .name("Stevie Ray Vaughan")
            .genre("Blues")
            .image("https://i1.wp.com/jessicakristie.com/wp-content/uploads/2012/02/StevieRayVaughan.jpg")
            .build()
    );

    List<ArtistRanked> fakeMethodResponse = Arrays.asList(
        ArtistRanked.builder()
            .idArtist(idArtist1)
            .name("Heroes del silencio")
            .genre("Rock")
            .image("https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/" +
                "1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg")
            .reproductions(100L)
            .build(),

        ArtistRanked.builder()
            .idArtist(idArtist2)
            .name("Metallica")
            .genre("Heavy metal")
            .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
            .reproductions(200L)
            .build(),

        ArtistRanked.builder()
            .idArtist(idArtist3)
            .name("Stevie Ray Vaughan")
            .genre("Blues")
            .image("https://i1.wp.com/jessicakristie.com/wp-content/uploads/2012/02/StevieRayVaughan.jpg")
            .reproductions(300L)
            .build()
    );

    when(artistService.getArtists()).thenReturn(fakeRepoResponse);
    when(artistService.artistReproductions(idArtist1)).thenReturn(100L);
    when(artistService.artistReproductions(idArtist2)).thenReturn(200L);
    when(artistService.artistReproductions(idArtist3)).thenReturn(300L);

    assertEquals(fakeMethodResponse, artistService.getTopArtists(2));
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
  public void createArtistFailByExistId() {
    ArtistRequest artistRequest = ArtistRequest.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtist = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtist);
    when(artistRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(java.util.Optional.of(fakeArtist));

    assertThrows(ArtistExistException.class, () -> artistService.createArtist(artistRequest));
  }

  @Test
  public void updateArtistSuccess() {
    ArtistRequest artistRequest = ArtistRequest.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtist = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtist);
    when(artistRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(java.util.Optional.of(fakeArtist));
    when(artistRepository.save(eq(fakeArtist))).thenReturn(fakeArtist);

    Artist artistResponse = artistService.updateArtist(artistRequest);

    assertEquals(fakeArtist, artistResponse);
  }

  @Test
  public void updateArtistFailByNotExistingId() {
    ArtistRequest artistRequest = ArtistRequest.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Artist fakeArtist = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();

    when(artistMapper.apply(eq(artistRequest))).thenReturn(fakeArtist);
    when(artistRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(Optional.empty());

    assertThrows(ArtistNotExistException.class, () -> artistService.updateArtist(artistRequest));
  }

  @Ignore
  @Test
  public void deleteArtist() {
  }
}