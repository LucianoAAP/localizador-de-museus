package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * Record representing museum creation.
 */
public record MuseumCreationDto(String name, String description, String address,
    String collectionType, String subject, String url, Coordinate coordinate) {

}
