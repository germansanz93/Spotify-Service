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
  void deleteAlbumsByArtist_IdArtist(Long id);

}
