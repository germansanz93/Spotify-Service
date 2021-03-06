package com.pinapp.spotifyservice.repository;

import com.pinapp.spotifyservice.domain.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArtistRepository extends CrudRepository<Artist, Long> {

}
