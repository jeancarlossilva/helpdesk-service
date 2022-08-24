package com.jean.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jean.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
