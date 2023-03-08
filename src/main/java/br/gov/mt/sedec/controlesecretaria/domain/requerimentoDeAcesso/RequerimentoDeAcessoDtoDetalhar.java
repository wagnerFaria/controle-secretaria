package br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso;

import br.gov.mt.sedec.controlesecretaria.domain.acesso.AcessoDtoDetalhar;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.ServidorDtoDetalhar;

import java.util.List;

public record RequerimentoDeAcessoDtoDetalhar(
        Long id,
        ServidorDtoDetalhar servidor,
        List<AcessoDtoDetalhar> acessoList,
        String nomeDoChefeImediato,
        String cargoDoChefeImediato,
        String nomeCoordenadorDaGestaoDePessoas,
        String nomeCoordenadorDaTi
) {
}
