package com.jean.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jean.helpdesk.domain.Chamado;
import com.jean.helpdesk.domain.Cliente;
import com.jean.helpdesk.domain.Tecnico;
import com.jean.helpdesk.domain.enums.Perfil;
import com.jean.helpdesk.domain.enums.Prioridade;
import com.jean.helpdesk.domain.enums.Status;
import com.jean.helpdesk.repositories.ChamadoRepostiroy;
import com.jean.helpdesk.repositories.ClienteRepository;
import com.jean.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepostiroy chamadoRepostiroy;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null,"Jean Silva", "80762637005","jeansilva083@gmail.com","123");
		tec1.addPerfi(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null,"Linus Trovalds", "02376311055","linustrovalds@gmail.com","123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.save(tec1);
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		chamadoRepostiroy.saveAll(Arrays.asList(c1));
		
	}

}
