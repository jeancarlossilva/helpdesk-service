package com.jean.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.helpdesk.domain.Chamado;
import com.jean.helpdesk.domain.dtos.ChamadoDTO;
import com.jean.helpdesk.repositories.ChamadoRepostiroy;
import com.jean.helpdesk.services.exceptions.ObjectNotFounException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepostiroy repostiroy;
	
	
	public Chamado findbyId(Integer id) {
		Optional<Chamado> obj = repostiroy.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFounException("Chamado não encontrado! id: " + id));
		
	}
	
	
	
	
}
