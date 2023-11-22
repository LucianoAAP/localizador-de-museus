package com.betrybe.museumfinder.solution;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CollectionTypeService service;

  @Test
  void testHist() throws Exception {
    String[] collectionTypes = new String[] { "história" };
    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(collectionTypes, 1L);
    
    Mockito.when(service.countByCollectionTypes("história")).thenReturn(collectionTypeCountMock);
    
    mockMvc.perform(get("/collections/count/história")).andExpect(status().isOk())
      .andExpect(jsonPath("$.collectionTypes").isArray())
      .andExpect(jsonPath("$.collectionTypes.length()").value(1))
      .andExpect(jsonPath("$.collectionTypes", Matchers.contains("história")))
      .andExpect(jsonPath("$.count").value(1));
    
    Mockito.verify(service).countByCollectionTypes("história");
  }
  
  @Test
  void testHistImag() throws Exception {
    String[] collectionTypes = new String[] { "hist", "imag" };
    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(collectionTypes, 237L);
    
    Mockito.when(service.countByCollectionTypes("hist,imag")).thenReturn(collectionTypeCountMock);
    
    mockMvc.perform(get("/collections/count/hist,imag")).andExpect(status().isOk())
      .andExpect(jsonPath("$.collectionTypes").isArray())
      .andExpect(jsonPath("$.collectionTypes.length()").value(2))
      .andExpect(jsonPath("$.collectionTypes", Matchers.containsInAnyOrder("hist", "imag")))
      .andExpect(jsonPath("$.count").value(237));
    
    Mockito.verify(service).countByCollectionTypes("hist,imag");
  }
  
  @Test
  void testNotFound() throws Exception {
    String[] collectionTypes = new String[] { "xablau" };
    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(collectionTypes, 0);
    
    Mockito.when(service.countByCollectionTypes("xablau")).thenReturn(collectionTypeCountMock);
    
    mockMvc.perform(get("/collections/count/xablau")).andExpect(status().isNotFound());
    
    Mockito.verify(service).countByCollectionTypes("xablau");
  }
}
