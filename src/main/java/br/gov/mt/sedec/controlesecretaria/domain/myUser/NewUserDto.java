package br.gov.mt.sedec.controlesecretaria.domain.myUser;

import jakarta.validation.constraints.NotBlank;

public record NewUserDto(@NotBlank String username, @NotBlank String password) {

}
