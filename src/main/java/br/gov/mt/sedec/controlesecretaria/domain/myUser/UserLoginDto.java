package br.gov.mt.sedec.controlesecretaria.domain.myUser;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(@NotBlank String username, @NotBlank String password) {
}
