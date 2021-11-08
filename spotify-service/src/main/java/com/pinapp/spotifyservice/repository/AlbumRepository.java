package com.pinapp.spotifyservice.repository;

import com.pinapp.spotifyservice.controller.request.AlbumRequest;
import com.pinapp.spotifyservice.domain.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

  Album save(AlbumRequest request);

}
