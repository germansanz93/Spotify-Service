package com.pinapp.spotifyservice.repository;

import com.pinapp.spotifyservice.domain.model.Track;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ITrackRepository extends CrudRepository<Track, Long> {
  @Modifying
  @Transactional
  void deleteTracksByArtist_IdArtist(Long id);

  @Modifying
  @Transactional
  void deleteTracksByAlbum_IdAlbum(Long id);
}
