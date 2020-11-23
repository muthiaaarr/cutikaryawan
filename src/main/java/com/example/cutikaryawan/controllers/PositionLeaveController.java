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
import com.example.cutikaryawan.models.PositionLeave;
import com.example.cutikaryawan.models.dtos.PositionLeaveDTO;
import com.example.cutikaryawan.repositories.PositionLeaveRepository;

@RestController
@RequestMapping("/api/position-leave")
public class PositionLeaveController {

	@Autowired
	PositionLeaveRepository positionLeaveRepository;
	
	ModelMapper mapper = new ModelMapper();
	
	// CREATE POSITION LEAVE
	@PostMapping("/create")
	public Map<String, Object> createPositionLeave(@RequestBody PositionLeaveDTO positionLeaveDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		PositionLeave positionLeave = new PositionLeave();
		positionLeave = mapper.map(positionLeaveDTO, PositionLeave.class);
		positionLeave.setCreatedBy("Admin");
		positionLeaveRepository.save(positionLeave);
		
		positionLeaveDTO.setPositionLeaveId(positionLeave.getPositionLeaveId());
		result.put("Message", "Create position leave success!");
		result.put("Data", positionLeaveDTO);
		
		return result;
	}
	
	// READ ALL POSITION LEAVE
	@GetMapping("/readAll")
	public Map<String, Object> readAllPositionLeave() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<PositionLeave> positionLeaveList = positionLeaveRepository.findAll();
		List<PositionLeaveDTO> positionLeaveDTOList = new ArrayList<PositionLeaveDTO>();
		
		for (PositionLeave pl : positionLeaveList) {
			PositionLeaveDTO positionLeaveDTO = mapper.map(pl, PositionLeaveDTO.class);
			positionLeaveDTOList.add(positionLeaveDTO);
		}
		
		result.put("Message", "Read all position leaves success!");
		result.put("Data", positionLeaveDTOList);
		result.put("Total items", positionLeaveDTOList.size());
		
		return result;
	}
	
	// READ POSITION LEAVE BY ID
	@GetMapping("/read")
	public Map<String, Object> readPositionLeaveById(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		PositionLeave positionLeave = positionLeaveRepository.findById(id).orElseThrow(() -> new
				IdNotFoundException(String.format("Position leave dengan id %s tidak dapat ditemukan", id)));
				
		PositionLeaveDTO positionLeaveDTO = mapper.map(positionLeave, PositionLeaveDTO.class);
		
		result.put("Message", "Read position leave by id success!");
		result.put("Data", positionLeaveDTO);
		
		return result;
	}
	
	// UPDATE POSITION LEAVE
	@PutMapping("/update")
	public Map<String, Object> updatePositionLeave(@RequestParam("id") Long id, @RequestBody PositionLeaveDTO positionLeaveDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		PositionLeave positionLeave = positionLeaveRepository.findById(id).orElseThrow(() -> new
				IdNotFoundException(String.format("Position leave dengan id %s tidak dapat ditemukan", id)));
		
		positionLeaveDTO.setPositionLeaveId(id);
		positionLeaveDTO.setCreatedBy(positionLeave.getCreatedBy());
		positionLeaveDTO.setCreatedAt(positionLeave.getCreatedAt());
		positionLeaveDTO.setUpdatedBy("Admin");
		
		positionLeave = mapper.map(positionLeaveDTO, PositionLeave.class);
		positionLeaveRepository.save(positionLeave);
		
		positionLeaveDTO.setPositionLeaveId(id);
		result.put("Message", "Update position leave success!");
		result.put("Data", positionLeaveDTO);
		
		return result;
	}
	
	// DELETE POSITION LEAVE
	@DeleteMapping("/delete")
	public Map<String, Object> deletePositionLeave(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		PositionLeave positionLeave = positionLeaveRepository.findById(id).orElseThrow(() -> new
				IdNotFoundException(String.format("Position leave dengan id %s tidak dapat ditemukan", id)));
		
		PositionLeaveDTO positionLeaveDTO = mapper.map(positionLeave, PositionLeaveDTO.class);
		
		positionLeaveRepository.delete(positionLeave);
		
		positionLeaveDTO.setPositionLeaveId(id);
		result.put("Message", "Delete position leave success!");
		result.put("Data", positionLeaveDTO);
		
		return result;
	}

}
