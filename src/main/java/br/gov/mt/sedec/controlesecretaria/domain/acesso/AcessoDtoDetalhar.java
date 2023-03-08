package br.gov.mt.sedec.controlesecretaria.domain.acesso;

import jakarta.validation.constraints.NotBlank;

public record AcessoDtoDetalhar(
        Long id,
        String descricao
) {
    public AcessoDtoDetalhar(Acesso acesso) {
        this(acesso.getId(), acesso.getDescricao());
    }
}
