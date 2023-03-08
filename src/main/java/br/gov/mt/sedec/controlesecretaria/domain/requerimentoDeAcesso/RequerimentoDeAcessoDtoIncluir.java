package br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso;

import br.gov.mt.sedec.controlesecretaria.domain.acesso.AcessoDtoIncluir;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.ServidorDtoIncluir;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RequerimentoDeAcessoDtoIncluir(
        @NotNull
        ServidorDtoIncluir servidor,
        @Size(min = 1)
        @NotNull
        List<AcessoDtoIncluir> acessoList,
        @NotBlank
        String nomeDoChefeImediato,
        @NotBlank
        String cargoDoChefeImediato,
        String nomeCoordenadorDaGestaoDePessoas,
        String nomeCoordenadorDaTi
) {
}
