package br.com.gsw.example.liquibase.poc.repository;

import br.com.gsw.example.liquibase.poc.domain.Poc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocRepository extends JpaRepository<Poc, Long> {
}