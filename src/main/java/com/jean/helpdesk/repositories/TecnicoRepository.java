package com.jean.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jean.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
