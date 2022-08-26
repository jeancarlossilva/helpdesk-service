package com.jean.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jean.helpdesk.domain.Pessoa;
import com.jean.helpdesk.domain.Cliente;
import com.jean.helpdesk.domain.dtos.ClienteDTO;
import com.jean.helpdesk.repositories.PessoaRepository;
import com.jean.helpdesk.repositories.ClienteRepository;
import com.jean.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jean.helpdesk.services.exceptions.ObjectNotFounException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository ClienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = ClienteRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFounException("Objeto não encontrado! Id: " + id));
			
	}


	public List<Cliente> findAll() {
		return ClienteRepository.findAll();
	}


	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
		validaPorCpfEEmail(clienteDTO);
		Cliente newObj = new Cliente(clienteDTO);
		return ClienteRepository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		
		oldObj = new Cliente(objDTO);
		
		return ClienteRepository.save(oldObj);
		
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de seviço e não pode ser lançado!");
		}
		
		ClienteRepository.deleteById(id);
	}


	private void validaPorCpfEEmail(ClienteDTO ClienteDTO) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(ClienteDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != ClienteDTO.getId()) {
			throw new DataIntegrityViolationException("CPf já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(ClienteDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != ClienteDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
	}


	


	

}
