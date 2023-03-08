package br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso;

import br.gov.mt.sedec.controlesecretaria.domain.acesso.Acesso;
import br.gov.mt.sedec.controlesecretaria.domain.acesso.AcessoDtoDetalhar;
import br.gov.mt.sedec.controlesecretaria.domain.acesso.AcessoDtoIncluir;
import br.gov.mt.sedec.controlesecretaria.domain.acesso.AcessoService;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.Servidor;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.ServidorDtoDetalhar;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.ServidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequerimentoDeAcessoService {

    private final RequerimentoDeAcessoRepository requerimentoDeAcessoRepository;

    private final ServidorService servidorService;
    private final AcessoService acessoService;

    public RequerimentoDeAcessoDtoDetalhar persist(RequerimentoDeAcessoDtoIncluir requerimento) {

        //verifica se o servidor jah exite
        Servidor servidor = servidorService.findByCpf(requerimento.servidor()
                .cpf());

        //salva servidor caso nao exista
        if (servidor == null) {
            servidor = servidorService.save(new Servidor(requerimento.servidor()));
        }
        // salva requerimento
        RequerimentoDeAcesso paraSalvar = new RequerimentoDeAcesso(requerimento);

        if (servidor.getId() != null)
            paraSalvar.setServidor(servidor);

        requerimentoDeAcessoRepository.save(paraSalvar);

        //salva acesso
        for (AcessoDtoIncluir dto : requerimento.acessoList()) {
            Acesso acesso = Acesso.builder()
                    .descricao(dto.descricao())
                    .requerimentoDeAcesso(paraSalvar)
                    .build();
            acessoService.save(acesso);
            paraSalvar.getAcessoList()
                    .add(acesso);
        }

        return new RequerimentoDeAcessoDtoDetalhar(
                paraSalvar.getId(),
                new ServidorDtoDetalhar(servidor),
                paraSalvar.getAcessoList()
                        .stream()
                        .map(AcessoDtoDetalhar::new)
                        .toList(),
                paraSalvar.getNomeDoChefeImediato(),
                paraSalvar.getCargoDoChefeImediato(),
                paraSalvar.getNomeCoordenadorDaGestaoDePessoas(),
                paraSalvar.getNomeCoordenadorDaTi());
    }

    private RequerimentoDeAcesso findById(Long id) {
        return requerimentoDeAcessoRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException(HttpStatus.BAD_REQUEST, "Requerimento com id: " + id + " não encontrado") {
                });
    }
}
