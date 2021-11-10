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
  @Query(value = "DELETE FROM track WHERE id_artist = ?1", nativeQuery = true)
  public void deleteByIdArtist(Long id);
}
