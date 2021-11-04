package com.pinapp.spotifyservice.domain.mappers;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.Track;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TrackMapper implements Function<TrackRequest, Track> {
  public Track apply(TrackRequest trackRequest) {
    return Track.builder()
        .id(trackRequest.getId())
        .name(trackRequest.getName())
        .idArtist(trackRequest.getIdArtist())
        .idAlbum(trackRequest.getIdAlbum())
        .duration(trackRequest.getDuration())
        .build();
  }
}
