package br.gov.mt.sedec.controlesecretaria.domain.myUser;

import br.gov.mt.sedec.controlesecretaria.domain.myRole.MyRole;
import br.gov.mt.sedec.controlesecretaria.domain.myRole.MyRoleDto;

import java.util.List;

public record UserDto(Long id, String username, List<MyRoleDto> roles) {
}
