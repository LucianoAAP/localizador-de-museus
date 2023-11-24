package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.evaluation.utils.TestHelpers;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;

@SpringBootTest
public class GetMuseumByIdServiceTest {
  @Autowired
  private MuseumService service;
  @MockBean
  private MuseumFakeDatabase database;

  @Test
  @DisplayName("Deve retornar o resultado correto")
  void test() {
    Museum museumMock = TestHelpers.createMockMuseum(1L);
    Optional<Museum> optionalMuseumMock = Optional.of(museumMock);
    
    Mockito.when(database.getMuseum(1L)).thenReturn(optionalMuseumMock);
    
    Museum museum = service.getMuseum(1L);
    
    assertEquals(museumMock.getId(), museum.getId());
    assertEquals(museumMock.getName(), museum.getName());
    assertEquals(museumMock.getDescription(), museum.getDescription());
    assertEquals(museumMock.getAddress(), museum.getAddress());
    assertEquals(museumMock.getCollectionType(), museum.getCollectionType());
    assertEquals(museumMock.getSubject(), museum.getSubject());
    assertEquals(museumMock.getUrl(), museum.getUrl());
    assertEquals(museumMock.getCoordinate(), museum.getCoordinate());

    Mockito.verify(database).getMuseum(1L);
  }

  @Test
  @DisplayName("Deve lançar exceção quando nada for encontrado")
  void testNotFound() {
    Optional<Museum> optionalMuseumMock = Optional.empty();
    
    Mockito.when(database.getMuseum(237L)).thenReturn(optionalMuseumMock);
    
    assertThrows(MuseumNotFoundException.class, () -> service.getMuseum(237L));
    
    Mockito.verify(database).getMuseum(237L);
  }
}
