package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
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
    Optional<Museum> museum = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);
    if (museum == null) {
      throw new MuseumNotFoundException("Museu não encontrado!");
    }
    return museum.get();
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
    // TODO Auto-generated method stub
    return null;
  }

}
