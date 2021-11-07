package com.pinapp.spotifyservice.repository;

import com.pinapp.spotifyservice.domain.model.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
}
