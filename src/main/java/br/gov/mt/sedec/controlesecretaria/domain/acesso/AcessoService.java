package br.gov.mt.sedec.controlesecretaria.domain.acesso;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AcessoService {

    private final AcessoRepository acessoRepository;

    public Acesso save(Acesso acesso) {
        return acessoRepository.save(acesso);
    }

    public Page<Acesso> findAll(Pageable page) {
        return acessoRepository.findAll(page);
    }

    public Acesso findById(Long id) {
        return acessoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação de acesso não encontrada: "+id));
    }

}