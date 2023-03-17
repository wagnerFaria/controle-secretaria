package br.gov.mt.sedec.controlesecretaria.domain.myRole;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyRoleService {

    private final MyRoleRepository repository;

    public MyRole findByIdOrElseThrowBadRequest(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum Role de ID: " + id + " encontrado"));
    }
}
