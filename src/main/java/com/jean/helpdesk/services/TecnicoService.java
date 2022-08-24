package com.jean.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.helpdesk.domain.Pessoa;
import com.jean.helpdesk.domain.Tecnico;
import com.jean.helpdesk.domain.dtos.TecnicoDTO;
import com.jean.helpdesk.repositories.PessoaRepository;
import com.jean.helpdesk.repositories.TecnicoRepository;
import com.jean.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jean.helpdesk.services.exceptions.ObjectNotFounException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFounException("Objeto não encontrado! Id: " + id));
			
	}


	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}


	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validaPorCpfEEmail(tecnicoDTO);
		Tecnico newObj = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		
		Tecnico oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		
		oldObj = new Tecnico(objDTO);
		
		return tecnicoRepository.save(oldObj);
		
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de seviço e não pode ser lançado!");
		}
		
		tecnicoRepository.deleteById(id);
	}


	private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("CPf já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
	}


	


	

}
