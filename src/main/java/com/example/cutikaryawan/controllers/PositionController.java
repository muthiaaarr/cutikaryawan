package com.example.cutikaryawan.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cutikaryawan.exceptions.IdNotFoundException;
import com.example.cutikaryawan.models.Position;
import com.example.cutikaryawan.models.dtos.PositionDTO;
import com.example.cutikaryawan.repositories.PositionRepository;

@RestController
@RequestMapping("/api/position")
public class PositionController {

	@Autowired
	PositionRepository positionRepository;
	
	ModelMapper mapper = new ModelMapper();
	
	// CREATE POSITION
	@PostMapping("/create")
	public Map<String, Object> createPosition(@RequestBody PositionDTO positionDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Position position = new Position();
		position = mapper.map(positionDTO, Position.class);
		position.setCreatedBy("Admin");
		positionRepository.save(position);
		
		positionDTO.setPositionId(position.getPositionId());
		result.put("Message", "Create position success!");
		result.put("Data", positionDTO);
		
		return result;
	}
	
	// READ ALL POSITION
	@GetMapping("/readAll")
	public Map<String, Object> readAllPosition() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Position> positionList = positionRepository.findAll();
		List<PositionDTO> positionDTOList = new ArrayList<PositionDTO>();
		
		for (Position p : positionList) {
			PositionDTO positionDTO = mapper.map(p, PositionDTO.class);
			positionDTOList.add(positionDTO);
		}
		
		result.put("Message", "Read all positions success!");
		result.put("Data", positionDTOList);
		result.put("Total items", positionDTOList.size());
		
		return result;
	}
	
	// READ POSITION BY ID
	@GetMapping("/read")
	public Map<String, Object> readPositionById(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Position position = positionRepository.findById(id).orElseThrow(() -> new
				IdNotFoundException(String.format("Position dengan id %s tidak dapat ditemukan", id)));
		
		PositionDTO positionDTO = mapper.map(position, PositionDTO.class);
		
		result.put("Message", "Read position by id success!");
		result.put("Data", positionDTO);
		
		return result;
	}
	
	// UPDATE POSITION
	@PutMapping("/update")
	public Map<String, Object> updatePosition(@RequestParam("id") Long id, @RequestBody PositionDTO positionDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Position position = positionRepository.findById(id).orElseThrow(() -> new
				IdNotFoundException(String.format("Position dengan id %s tidak dapat ditemukan", id)));
		
		positionDTO.setPositionId(id);
		positionDTO.setCreatedBy(position.getCreatedBy());
		positionDTO.setCreatedAt(position.getCreatedAt());
		positionDTO.setUpdatedBy("Admin");
		
		position = mapper.map(positionDTO, Position.class);
		positionRepository.save(position);
		
		positionDTO.setPositionId(id);
		result.put("Message", "Update position success!");
		result.put("Data", positionDTO);
		
		return result;
	}
	
	// DELETE POSITION
	@DeleteMapping("/delete")
	public Map<String, Object> deletePosition(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Position position = positionRepository.findById(id).orElseThrow(() -> new
				IdNotFoundException(String.format("Position dengan id %s tidak dapat ditemukan", id)));
		
		PositionDTO positionDTO = mapper.map(position, PositionDTO.class);
		
		positionRepository.delete(position);
		
		positionDTO.setPositionId(id);
		result.put("Message", "Delete position success!");
		result.put("Data", positionDTO);
		
		return result;
	}
	
}
