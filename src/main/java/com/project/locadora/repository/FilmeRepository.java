package com.project.locadora.repository;

import com.project.locadora.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Optional<Filme> findByTituloIgnoreCase(String titulo);

    List<Filme> findByQuantidadeGreaterThan(int quantidade);
}
