package com.betrybe.museumfinder.solution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;;

@SpringBootTest
@AutoConfigureMockMvc
class CollectionTypeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void testHist() throws Exception {
    mockMvc.perform(get("/collections/count/história")).andExpect(status().isOk())
      .andExpect(jsonPath("$.collectionTypes").isArray())
      .andExpect(jsonPath("$.collectionTypes.length()").value(1))
      .andExpect(jsonPath("$.collectionTypes", Matchers.contains("história")))
      .andExpect(jsonPath("$.count").value(387));
  }
  
  @Test
  void testHistImag() throws Exception {
    mockMvc.perform(get("/collections/count/hist,imag")).andExpect(status().isOk())
      .andExpect(jsonPath("$.collectionTypes").isArray())
      .andExpect(jsonPath("$.collectionTypes.length()").value(2))
      .andExpect(jsonPath("$.collectionTypes", Matchers.containsInAnyOrder("hist", "imag")))
      .andExpect(jsonPath("$.count").value(492));
  }
  
  @Test
  void testNotFound() throws Exception {
    mockMvc.perform(get("/collections/count/xablau")).andExpect(status().isNotFound());
  }
}
