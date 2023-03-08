package br.gov.mt.sedec.controlesecretaria.domain.servidor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
