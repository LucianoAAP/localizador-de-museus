package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Museum service.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  @Autowired
  private MuseumFakeDatabase museumFakeDatabase;
  
  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException("Coordenada inválida!");
    }
    try {      
      Optional<Museum> museum = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);
      return museum.get();
    } catch (NoSuchElementException e) {
      throw new MuseumNotFoundException("Museu não encontrado!");
    }
  }
  
  @Override
  public Museum createMuseum(Museum museum) {
    if (!CoordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException("Coordenada inválida!");
    }
    return museumFakeDatabase.saveMuseum(museum);
  }
  
  @Override
  public Museum getMuseum(Long id) {
    try {
      Optional<Museum> museum = museumFakeDatabase.getMuseum(id);
      return museum.get();
    } catch (NoSuchElementException e) {
      throw new MuseumNotFoundException("Museu não encontrado!");
    }
  }

}
