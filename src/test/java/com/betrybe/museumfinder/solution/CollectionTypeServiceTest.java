package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;

@SpringBootTest
public class CollectionTypeServiceTest {
  @Autowired
  CollectionTypeService service;
  @MockBean
  private MuseumFakeDatabase database;

  @Test
  @DisplayName("Deve retornar o valor correto para o parâmetro 'história'")
  void testHist() {
    String[] collectionTypes = new String[] { "história" };
    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(collectionTypes, 1L);
    
    Mockito.when(database.countByCollectionType("história")).thenReturn(1L);
    
    CollectionTypeCount collectionTypeCount = service.countByCollectionTypes("história");
    
    assertArrayEquals(collectionTypeCountMock.collectionTypes(), collectionTypeCount.collectionTypes());
    assertEquals(collectionTypeCountMock.count(), collectionTypeCount.count());
    
    Mockito.verify(database).countByCollectionType("história");
  }
  
  @Test
  @DisplayName("Deve retornar o valor correto para o parâmetro 'hist,imag'")
  void testHistImag() {
    String[] collectionTypes = new String[] { "hist", "imag" };
    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(collectionTypes, 30L);
    
    Mockito.when(database.countByCollectionType("hist")).thenReturn(23L);
    Mockito.when(database.countByCollectionType("imag")).thenReturn(7L);
    
    CollectionTypeCount collectionTypeCount = service.countByCollectionTypes("hist,imag");
    
    assertArrayEquals(collectionTypeCountMock.collectionTypes(), collectionTypeCount.collectionTypes());
    assertEquals(collectionTypeCountMock.count(), collectionTypeCount.count());
    
    Mockito.verify(database).countByCollectionType("hist");
    Mockito.verify(database).countByCollectionType("imag");
  }
  
  @Test
  @DisplayName("Deve retornar o valor correto para um parâmetro que não existe")
  void testNotFound() {
    String[] collectionTypes = new String[] { "xablau" };
    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(collectionTypes, 0);
    
    Mockito.when(database.countByCollectionType("xablau")).thenReturn(0L);
    
    CollectionTypeCount collectionTypeCount = service.countByCollectionTypes("xablau");
    
    assertArrayEquals(collectionTypeCountMock.collectionTypes(), collectionTypeCount.collectionTypes());
    assertEquals(collectionTypeCountMock.count(), collectionTypeCount.count());
    
    Mockito.verify(database).countByCollectionType("xablau");
  }
}
