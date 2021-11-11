package com.pinapp.spotifyservice.repository;

import com.pinapp.spotifyservice.domain.model.Album;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IAlbumRepository extends CrudRepository<Album, Long> {
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM album WHERE id_artist = ?1", nativeQuery = true)
  void deleteByIdArtist(Long id);
}
