package br.gov.mt.sedec.controlesecretaria.domain.servidor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServidorRepository extends JpaRepository<Servidor, Long> {
    Optional<Servidor> findByCpf(String cpf);
}
