package br.gov.mt.sedec.controlesecretaria.domain.servidor;

import java.time.LocalDate;

public record ServidorDtoDetalhar(
        Long id,
        String nome,
        String unidadeDeLotacao,
        LocalDate dataNascimento,
        String matricula,
        String cpf,
        String registroGeral,
        String telefone
) {
    public ServidorDtoDetalhar(Servidor servidor) {
        this(
                servidor.getId(),
                servidor.getNome(),
                servidor.getUnidadeDeLotacao(),
                servidor.getDataNascimento(),
                servidor.getMatricula(),
                servidor.getCpf(),
                servidor.getRegistroGeral(),
                servidor.getTelefone()
        );
    }
}
