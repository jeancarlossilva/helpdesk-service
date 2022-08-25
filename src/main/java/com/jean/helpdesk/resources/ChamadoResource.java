package com.jean.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jean.helpdesk.domain.Chamado;
import com.jean.helpdesk.domain.dtos.ChamadoDTO;
import com.jean.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado obj = service.findbyId(id);
		
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
		
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> listaChamados = service.findAll();
		
		List<ChamadoDTO> listaChamadosDTO = listaChamados.stream().map(c -> new ChamadoDTO(c)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaChamadosDTO);
		
	}

}
