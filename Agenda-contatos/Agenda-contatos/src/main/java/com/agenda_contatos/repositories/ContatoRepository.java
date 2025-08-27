package com.agenda_contatos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda_contatos.model.Contato;

import jakarta.transaction.Transactional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByNome(String nome);

    @Transactional
    void deleteByNome(String nome);
}
