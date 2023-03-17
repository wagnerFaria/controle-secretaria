package br.gov.mt.sedec.controlesecretaria.util;

import br.gov.mt.sedec.controlesecretaria.domain.myRole.MyRole;

public class MyRoleUtil {

    public static MyRole returnSampleRole() {
        return MyRole.builder()
                .id(1L)
                .name("ROLE_USER")
                .build();
    }
}
