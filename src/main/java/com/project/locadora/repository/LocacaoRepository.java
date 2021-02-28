package com.project.locadora.repository;

import com.project.locadora.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
