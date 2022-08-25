package com.jean.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.helpdesk.domain.Chamado;
import com.jean.helpdesk.domain.Cliente;
import com.jean.helpdesk.domain.Tecnico;
import com.jean.helpdesk.domain.dtos.ChamadoDTO;
import com.jean.helpdesk.domain.enums.Prioridade;
import com.jean.helpdesk.domain.enums.Status;
import com.jean.helpdesk.repositories.ChamadoRepostiroy;
import com.jean.helpdesk.services.exceptions.ObjectNotFounException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepostiroy repostiroy;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired ClienteService clienteService;
	
	
	public Chamado findbyId(Integer id) {
		Optional<Chamado> obj = repostiroy.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFounException("Chamado não encontrado! id: " + id));
		
	}


	public List<Chamado> findAll() {
		return repostiroy.findAll();
	}


	public Chamado create(@Valid ChamadoDTO objDTO) {
		
		return repostiroy.save(newChamado(objDTO)) ;
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findbyId(id);
		
		oldObj = newChamado(objDTO);
		
		return repostiroy.save(oldObj);
	}
	
	
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
	
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;
		
	}


	
	
	
	
	
}
