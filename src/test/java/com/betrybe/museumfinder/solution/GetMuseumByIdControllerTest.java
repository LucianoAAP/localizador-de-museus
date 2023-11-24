package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.betrybe.museumfinder.evaluation.utils.TestHelpers;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;

@SpringBootTest
@AutoConfigureMockMvc
public class GetMuseumByIdControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private MuseumService service;

  @Test
  @DisplayName("Deve retornar o resultado correto")
  void test() throws Exception {
    Museum museumMock = TestHelpers.createMockMuseum(1L);
    
    Mockito.when(service.getMuseum(1L)).thenReturn(museumMock);
    
    mockMvc.perform(get(String.format("/museums/%s", museumMock.getId())))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(museumMock.getId()))
      .andExpect(jsonPath("$.name").value(museumMock.getName()))
      .andExpect(jsonPath("$.description").value(museumMock.getDescription()))
      .andExpect(jsonPath("$.address").value(museumMock.getAddress()))
      .andExpect(jsonPath("$.collectionType").value(museumMock.getCollectionType()))
      .andExpect(jsonPath("$.subject").value(museumMock.getSubject()))
      .andExpect(jsonPath("$.url").value(museumMock.getUrl()))
      .andExpect(jsonPath("$.coordinate.latitude").value(museumMock.getCoordinate().latitude()))
      .andExpect(jsonPath("$.coordinate.longitude").value(museumMock.getCoordinate().longitude()));
  }

  @Test
  @DisplayName("Deve retornar 404 quando nada for encontrado")
  void testNotFound() throws Exception {
    Mockito.when(service.getMuseum(237L))
      .thenThrow(new MuseumNotFoundException("Museu não encontrado!"));
    
    mockMvc.perform(get("/museums/237")).andExpect(status().isNotFound())
      .andExpect(content().string("Museu não encontrado!"));
  }
}
