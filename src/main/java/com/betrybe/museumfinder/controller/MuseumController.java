package com.betrybe.museumfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;

@RestController
@RequestMapping(value="/museums")
public class MuseumController {
	@Autowired
	private MuseumServiceInterface museumService;

	@PostMapping
	public ResponseEntity<Museum> createMuseum(MuseumDto museumDto) {
		Museum museum = ModelDtoConverter.dtoToModel(museumDto);
		museum = museumService.createMuseum(museum);
		return ResponseEntity.status(HttpStatus.CREATED).body(museum);
	}
}
