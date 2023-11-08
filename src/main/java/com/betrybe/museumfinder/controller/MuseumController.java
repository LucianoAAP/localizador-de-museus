package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Museum controller.
 */
@RestController
@RequestMapping(value = "/museums")
public class MuseumController {
  @Autowired
  private MuseumServiceInterface museumService;

  /**
   * Route to create museum.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(MuseumDto museumDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumDto);
    museum = museumService.createMuseum(museum);
    return ResponseEntity.status(HttpStatus.CREATED).body(museum);
  }
  
  /**
   * Route to get the closest museum.
   */
  @GetMapping(value = "/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam(name = "lat") Double lat, @RequestParam(name = "lng") Double lng,
      @RequestParam(name = "max_dist_km") Double maxDistKm) {
    Coordinate coordinate = new Coordinate(lat, lng);
    Museum museum = museumService.getClosestMuseum(coordinate, maxDistKm);
    MuseumDto response = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.ok(response);
  }
  
  /**
   * Route to get museum by id.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<MuseumDto> getById(@PathVariable Long id) {
    Museum museum = museumService.getMuseum(id);
    MuseumDto response = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.ok(response);
  }
}
