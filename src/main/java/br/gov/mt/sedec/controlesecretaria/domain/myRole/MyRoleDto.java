package br.gov.mt.sedec.controlesecretaria.domain.myRole;

public record MyRoleDto(Long id, String name) {

    public MyRoleDto(MyRole role) {
        this(role.getId(), role.getName());

    }
}
