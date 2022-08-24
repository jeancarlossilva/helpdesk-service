package com.jean.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jean.helpdesk.domain.Tecnico;
import com.jean.helpdesk.domain.dtos.TecnicoDTO;
import com.jean.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findBayId(@PathVariable Integer id){
		
		Tecnico obj = tecnicoService.findById(id);
		
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	
	}

}
