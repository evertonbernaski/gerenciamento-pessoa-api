package com.projetopessoa.pessoaapi.repository;

import com.projetopessoa.pessoaapi.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
