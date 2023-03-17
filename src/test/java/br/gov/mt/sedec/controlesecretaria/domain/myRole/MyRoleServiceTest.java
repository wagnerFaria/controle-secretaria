package br.gov.mt.sedec.controlesecretaria.domain.myRole;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RequiredArgsConstructor
class MyRoleServiceTest {

    @Autowired
    private MyRoleService myRoleService;

    @Test
    @DisplayName("Should return ROLE_USER when successful")
    void findByIdOrElseThrowBadRequestShouldReturnRoleWhenSuccessful() {
        MyRole role = myRoleService.findByIdOrElseThrowBadRequest(1L);
        assertThat(role.getName()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("Should return ResponseStatusException when fail")
    void findByIdOrElseThrowBadRequestShouldReturnResponseStatusExceptionWhenFail() {
        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> myRoleService.findByIdOrElseThrowBadRequest(99L));
    }
}