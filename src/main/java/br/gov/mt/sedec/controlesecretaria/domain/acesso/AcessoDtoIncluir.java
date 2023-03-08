package br.gov.mt.sedec.controlesecretaria.domain.acesso;

import jakarta.validation.constraints.NotBlank;

public record AcessoDtoIncluir(

        @NotBlank
        String descricao
) {
}
