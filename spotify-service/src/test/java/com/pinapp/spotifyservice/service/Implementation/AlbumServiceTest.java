package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.mapper.AlbumMapper;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.exception.AlbumExistException;
import com.pinapp.spotifyservice.exception.AlbumNotExistException;
import com.pinapp.spotifyservice.repository.IAlbumRepository;
import com.pinapp.spotifyservice.repository.ITrackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AlbumServiceTest {


  @InjectMocks
  @Spy
  public AlbumService albumService;

  @Mock
  public IAlbumRepository albumRepository;

  @Mock
  public ITrackRepository trackRepository;

  @Mock
  public AlbumMapper albumMapper;

  @Mock
  public EntityManager entityManager;

  public List<Artist> fakeArtistsList = Arrays.asList(
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

  List<Album> fakeRepoResponse = Arrays.asList(
      Album.builder().idAlbum(1L).artist(fakeArtistsList.get(0)).name("La apariencia no es sincera").build(),
      Album.builder().idAlbum(2L).artist(fakeArtistsList.get(1)).name("Ride the lightning").build(),
      Album.builder().idAlbum(3L).artist(fakeArtistsList.get(1)).name("... And justice for all").build(),
      Album.builder().idAlbum(4L).artist(fakeArtistsList.get(2)).name("Texas flood").build()
  );

  Artist fakeArtist = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
  AlbumRequest albumRequest = AlbumRequest.builder().idAlbum(1L).idArtist(1L).name("La esquina del infinito").build();
  Album fakeAlbum = Album.builder().idAlbum(1L).artist(fakeArtist).name("La apariencia no es sincera").build();


  @Test
  public void getAlbumsSuccess() {

    when(albumRepository.findAll()).thenReturn(fakeRepoResponse);

    assertEquals(fakeRepoResponse, albumService.getAlbums());
  }

  @Test
  public void getAlbumSuccess() {
    Artist fakeArtist = Artist.builder().idArtist(1L).name("La renga").genre("Rock").image("ImageURL").build();
    Album fakeAlbum = Album.builder().idAlbum(1L).artist(fakeArtist).name("La apariencia no es sincera").build();

    when(albumRepository.findById(fakeAlbum.getIdAlbum())).thenReturn(Optional.of(fakeAlbum));

    assertEquals(albumService.getAlbum(fakeAlbum.getIdAlbum()), fakeAlbum);
  }

  @Test
  public void getAlbumFailByNotExistsId() {
    when(albumRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(AlbumNotExistException.class, () -> albumService.getAlbum(1L));
  }

  @Test
  public void createAlbumSuccess() {
    when(albumMapper.apply(eq(albumRequest))).thenReturn(fakeAlbum);
    when(albumRepository.findById(eq(fakeAlbum.getIdAlbum()))).thenReturn(java.util.Optional.of(fakeAlbum));
    when(albumRepository.save(eq(fakeAlbum))).thenReturn(fakeAlbum);

    Album albumResponse = albumService.updateAlbum(albumRequest);

    assertEquals(fakeAlbum, albumResponse);
  }

  @Test
  public void createAlbumFailByExistsId() {

    when(albumMapper.apply(eq(albumRequest))).thenReturn(fakeAlbum);
    when(albumRepository.findById(eq(fakeArtist.getIdArtist()))).thenReturn(java.util.Optional.of(fakeAlbum));

    assertThrows(AlbumExistException.class, () -> albumService.createAlbum(albumRequest));

  }

  @Test
  public void updateAlbumSuccess() {

    when(albumMapper.apply(eq(albumRequest))).thenReturn(fakeAlbum);
    when(albumRepository.findById(eq(fakeAlbum.getIdAlbum()))).thenReturn(java.util.Optional.of(fakeAlbum));
    when(albumRepository.save(eq(fakeAlbum))).thenReturn(fakeAlbum);

    Album albumResponse = albumService.updateAlbum(albumRequest);

    assertEquals(fakeAlbum, albumResponse);
  }

  @Test
  public void updateAlbumFailByIdNotExists() {

    when(albumMapper.apply(eq(albumRequest))).thenReturn(fakeAlbum);
    when(albumRepository.findById(eq(fakeAlbum.getIdAlbum()))).thenReturn(Optional.empty());

    assertThrows(AlbumNotExistException.class, () -> albumService.updateAlbum(albumRequest));
  }

  @Test
  public void deleteAlbum() {

    when(albumRepository.findById(eq(fakeAlbum.getIdAlbum()))).thenReturn(java.util.Optional.of(fakeAlbum));

    albumService.deleteAlbum(fakeArtist.getIdArtist());

    verify(trackRepository, times(1)).deleteTracksByAlbum_IdAlbum(eq(fakeAlbum.getIdAlbum()));
    verify(albumRepository, times(1)).deleteById(eq(fakeAlbum.getIdAlbum()));
  }
}