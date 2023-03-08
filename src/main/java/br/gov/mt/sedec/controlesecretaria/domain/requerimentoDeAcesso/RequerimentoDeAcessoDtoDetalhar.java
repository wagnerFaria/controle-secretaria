package br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso;

import br.gov.mt.sedec.controlesecretaria.domain.acesso.Acesso;
import br.gov.mt.sedec.controlesecretaria.domain.acesso.AcessoDtoDetalhar;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.Servidor;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.ServidorDtoDetalhar;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

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
