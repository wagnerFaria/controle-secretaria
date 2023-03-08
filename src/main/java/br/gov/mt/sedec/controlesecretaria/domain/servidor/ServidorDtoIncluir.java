package br.gov.mt.sedec.controlesecretaria.domain.servidor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ServidorDtoIncluir(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String unidadeDeLotacao,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento,
        @NotBlank
        String matricula,
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        String registroGeral,
        @NotBlank
        String telefone
) {
}
