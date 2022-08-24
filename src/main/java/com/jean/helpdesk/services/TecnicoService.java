package com.jean.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.helpdesk.domain.Tecnico;
import com.jean.helpdesk.domain.dtos.TecnicoDTO;
import com.jean.helpdesk.repositories.TecnicoRepository;
import com.jean.helpdesk.services.exceptions.ObjectNotFounException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFounException("Objeto não encontrado! Id: " + id));
			
	}


	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}


	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		Tecnico newObj = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(newObj);
	}

}
