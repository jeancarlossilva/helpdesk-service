package com.jean.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jean.helpdesk.domain.Chamado;

public interface ChamadoRepostiroy extends JpaRepository<Chamado, Integer> {

}
