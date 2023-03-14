package br.gov.mt.sedec.controlesecretaria.domain.servidor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServidorService {

    private final ServidorRepository servidorRepository;

    public Servidor findByCpf(String cpf) {
        Optional<Servidor> servidor = servidorRepository.findByCpf(cpf);
        return servidor.isPresent() ? servidor.get() : null;
    }

    public Servidor save(Servidor servidor) {
        return servidorRepository.save(servidor);
    }

    public Page<Servidor> findAll(Pageable page) {
        return servidorRepository.findAll(page);
    }

    public Servidor findById(Long id) {
        return servidorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servidor de ID: " + id + " não encontrado"));
    }
}