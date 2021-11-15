package com.pinapp.spotifyservice.service.Implementation;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.mapper.TrackMapper;
import com.pinapp.spotifyservice.domain.model.Album;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.Track;
import com.pinapp.spotifyservice.exception.TrackExistException;
import com.pinapp.spotifyservice.exception.TrackNotExistException;
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
public class TrackServiceTest {

  @InjectMocks
  @Spy
  public TrackService trackService;

  @Mock
  public ITrackRepository trackRepository;

  @Mock
  public TrackMapper trackMapper;

  List<Artist> fakeArtistsList = Arrays.asList(
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

  List<Album> fakeAlbumsList = Arrays.asList(
      Album.builder().idAlbum(1L).artist(fakeArtistsList.get(0)).name("La apariencia no es sincera").build(),
      Album.builder().idAlbum(2L).artist(fakeArtistsList.get(1)).name("Ride the lightning").build(),
      Album.builder().idAlbum(3L).artist(fakeArtistsList.get(1)).name("... And justice for all").build(),
      Album.builder().idAlbum(4L).artist(fakeArtistsList.get(2)).name("Texas flood").build()
  );

  List<Track> fakeTrackList =  Arrays.asList(
      Track.builder()
          .id(1L)
          .name("La apariencia no es sincera")
          .artist(fakeArtistsList.get(0))
          .album(fakeAlbumsList.get(0))
          .duration(421800L)
          .reproductions(1123L)
          .build(),
      Track.builder()
          .id(2L)
          .name("For whom the bell tolls")
          .artist(fakeArtistsList.get(1))
          .album(fakeAlbumsList.get(1))
          .duration(306000L)
          .reproductions(3213L)
          .build(),
      Track.builder()
          .id(3L)
          .name("...And justice for all")
          .artist(fakeArtistsList.get(1))
          .album(fakeAlbumsList.get(2))
          .duration(567600L)
          .reproductions(5413L)
          .build(),
      Track.builder()
          .id(4L)
          .name("Pride and Joy")
          .artist(fakeArtistsList.get(2))
          .album(fakeAlbumsList.get(3))
          .duration(204000L)
          .reproductions(3413L)
          .build()
  );

  List<Track> fakeSortedTrackList =  Arrays.asList(
      Track.builder()
          .id(3L)
          .name("...And justice for all")
          .artist(fakeArtistsList.get(1))
          .album(fakeAlbumsList.get(2))
          .duration(567600L)
          .reproductions(5413L)
          .build(),
      Track.builder()
          .id(4L)
          .name("Pride and Joy")
          .artist(fakeArtistsList.get(2))
          .album(fakeAlbumsList.get(3))
          .duration(204000L)
          .reproductions(3413L)
          .build()
  );

  List<Track> fakeSortedArtistTrackList = Arrays.asList(
      Track.builder()
          .id(3L)
          .name("...And justice for all")
          .artist(fakeArtistsList.get(1))
          .album(fakeAlbumsList.get(2))
          .duration(567600L)
          .reproductions(5413L)
          .build(),
      Track.builder()
          .id(2L)
          .name("For whom the bell tolls")
          .artist(fakeArtistsList.get(1))
          .album(fakeAlbumsList.get(1))
          .duration(306000L)
          .reproductions(3213L)
          .build()
  );

  Artist fakeArtist = Artist.builder()
      .idArtist(1L)
      .name("Heroes del silencio")
      .genre("Rock")
      .image("https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/" +
          "1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg")
      .build();

  Album fakeAlbum = Album.builder().idAlbum(1L).artist(fakeArtist).name("La apariencia no es sincera").build();

  Track fakeTrack = Track.builder()
      .id(1L)
      .name("La apariencia no es sincera")
      .album(fakeAlbum)
      .artist(fakeArtist)
      .duration(421800L)
      .reproductions(1123L)
      .build();

  TrackRequest fakeTrackRequest = TrackRequest.builder().id(1L)
      .name("La apariencia no es sincera")
      .idAlbum(fakeAlbum.getIdAlbum())
      .idArtist(fakeArtist.getIdArtist())
      .duration(421800L)
      .build();

  @Test
  public void getTracksSuccess() {

    when(trackRepository.findAll()).thenReturn(fakeTrackList);

    assertEquals(fakeTrackList, trackService.getTracks());

  }

  @Test
  public void getTracksByArtistSuccess() {

    when(trackService.getTracks()).thenReturn(fakeTrackList);

    assertEquals(fakeTrackList.subList(1,3), trackService.getTracksByArtist(fakeArtistsList.get(1).getIdArtist()));

  }

  @Test
  public void getArtistRankedTracksSuccess() {

    when(trackService.getTracksByArtist(fakeArtistsList.get(1).getIdArtist())).thenReturn(fakeTrackList.subList(1,3));

    assertEquals(fakeSortedArtistTrackList, trackService.getArtistRankedTracks(fakeArtistsList.get(1).getIdArtist()));

  }

  @Test
  public void getRankedTracksSuccess() {

    when(trackService.getTracks()).thenReturn(fakeTrackList);
    assertEquals(fakeSortedTrackList, trackService.getRankedTracks(2));
  }

  @Test
  public void getTrackSuccess() {

    when(trackRepository.findById(fakeTrack.getId())).thenReturn(Optional.of(fakeTrack));

    assertEquals(trackService.getTrack(fakeTrack.getId()), fakeTrack);
  }

  @Test
  public void getTrackFailByIdNotExists(){
    when(trackRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(TrackNotExistException .class, () -> trackService.getTrack(1L));
  }

  @Test
  public void createTrackSuccess() {

    when(trackMapper.apply(fakeTrackRequest)).thenReturn(fakeTrack);

    when(trackRepository.findById(fakeTrack.getId())).thenReturn(Optional.empty());

    when(trackRepository.save(fakeTrack)).thenReturn(fakeTrack);

    assertEquals(trackService.createTrack(fakeTrackRequest), fakeTrack);
  }

  @Test
  public void createTrackFailByExistId(){

    when(trackMapper.apply(fakeTrackRequest)).thenReturn(fakeTrack);
    when(trackRepository.findById(fakeTrack.getId())).thenReturn(Optional.of(fakeTrack));

    assertThrows(TrackExistException.class, () -> trackService.createTrack(fakeTrackRequest));

  }

  @Test
  public void updateTrackSuccess() {

    when(trackMapper.apply(fakeTrackRequest)).thenReturn(fakeTrack);

    when(trackRepository.findById(fakeTrack.getId())).thenReturn(Optional.of(fakeTrack));

    when(trackRepository.save(fakeTrack)).thenReturn(fakeTrack);

    assertEquals(trackService.updateTrack(fakeTrackRequest), fakeTrack);
  }

  @Test
  public void updateTrackFailByNotExistId(){

    when(trackMapper.apply(fakeTrackRequest)).thenReturn(fakeTrack);

    when(trackRepository.findById(fakeTrack.getId())).thenReturn(Optional.empty());

    assertThrows(TrackNotExistException.class, () -> trackService.updateTrack(fakeTrackRequest));
  }

  @Test
  public void deleteTrackSuccess() {

    when(trackRepository.findById(eq(fakeTrack.getId()))).thenReturn(Optional.of(fakeTrack));

    trackService.deleteTrack(fakeArtist.getIdArtist());
    verify(trackRepository, times(1)).deleteById(fakeTrack.getId());
  }

  @Test
  public void deleteTrackFailByNotExistId(){

    when(trackRepository.findById(eq(fakeTrack.getId()))).thenReturn(Optional.empty());

    assertThrows(TrackNotExistException.class, () -> trackService.deleteTrack(fakeArtist.getIdArtist()));
  }

  @Test
  public void playTrackSuccess() {

    when(trackRepository.findById(eq(fakeTrack.getId()))).thenReturn(Optional.of(fakeTrack));

    assertEquals(trackService.playTrack(fakeTrack.getId()), fakeTrack);
    verify(trackRepository, times(1)).save(fakeTrack);
  }

  @Test
  public void playTrackFailByNotExistsId() {

    when(trackRepository.findById(eq(fakeTrack.getId()))).thenReturn(Optional.empty());
    assertThrows(TrackNotExistException.class, () -> trackService.playTrack(fakeTrack.getId()));

  }
}