package br.gov.mt.sedec.controlesecretaria.domain.myUser;

import br.gov.mt.sedec.controlesecretaria.domain.myRole.MyRoleService;
import br.gov.mt.sedec.controlesecretaria.util.MyRoleUtil;
import br.gov.mt.sedec.controlesecretaria.util.MyUserUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class MyUserServiceTest {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MyRoleService myRoleServiceMock;

    @MockBean
    private MyUserRepository myUserRepositoryMock;

    @Test
    @DisplayName("Persist new User When successful")
    void persistUserWhenSuccessful() {

        BDDMockito.when(myRoleServiceMock.findByIdOrElseThrowBadRequest(ArgumentMatchers.anyLong()))
                .thenReturn(MyRoleUtil.returnSampleRole());


        BDDMockito.when(myUserRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(MyUserUtil.returnSampleUser());

        NewUserDto dto = new NewUserDto("testuser@gmail.com", "123456");

        MyUser user = myUserService.persistUser(dto);

        Assertions.assertThat(user.getId())
                .isNotNull();
    }

    @Test
    @DisplayName("Load UserDetail by username WHEN successful")
    void loadUserByUsernameWhenSuccessful() {
        BDDMockito.when(myUserRepositoryMock.findByUsername(anyString()))
                .thenReturn(MyUserUtil.returnSampleUser());

        UserDetails userDetails = myUserService.loadUserByUsername(MyUserUtil.returnSampleUser()
                .getUsername());

        Assertions.assertThat(userDetails)
                .isNotNull();
    }

    @Test
    @DisplayName("Find MyUser by ID When Successfull")
    void findByIdOrElseThrowBadRequestWhenSuccessful() {
        BDDMockito.when(myUserRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.of(MyUserUtil.returnSampleUser()));

        MyUser user = myUserService.findByIdOrElseThrowBadRequest(1L);

        Assertions.assertThat(user)
                .isNotNull();
    }

    @Test
    @DisplayName("Find MyUser by ID Throws ResponseStatusException When fail")
    void findByIdOrElseThrowBadRequestThrowsBadRequestWhenFail() {
        BDDMockito.when(myUserRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> myUserService.findByIdOrElseThrowBadRequest(1L));
    }


    @Test
    @DisplayName("Find MyUser by username return MyUser When Successfull")
    void findByUsernameOrElseThrowBadRequestReturnMyUserWhenSuccessful() {
        BDDMockito.when(myUserRepositoryMock.findMyUserByUsername(anyString()))
                .thenReturn(Optional.of(MyUserUtil.returnSampleUser()));

        MyUser user = myUserService.findByUsernameOrElseThrowBadRequest(MyUserUtil.returnSampleUser()
                .getUsername());

        Assertions.assertThat(user)
                .isNotNull();
    }

    @Test
    @DisplayName("Find MyUser By Username throws ResponseStatusException when Fail")
    void findByUsernameOrElseThrowBadRequestThrowsResponseStatusExceptionWhenFail() {
        BDDMockito.when(myUserRepositoryMock.findMyUserByUsername(anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> myUserService.findByUsernameOrElseThrowBadRequest("test"));
    }

    @Test
    @DisplayName("Update user password when successful")
    @WithMockUser
    void updateUserPasswordWhenSuccessful() {
        BDDMockito.when(myUserRepositoryMock.findMyUserByUsername(anyString()))
                .thenReturn(Optional.of(MyUserUtil.returnSampleUser()));

        BDDMockito.when(myUserRepositoryMock.save(any()))
                .thenReturn(MyUserUtil.returnSamplePasswordUpdatedUser());

        MyUser updatedUser = myUserService.updateUserPassword(new UpdateUserPasswordDto("123456", "1234567"));

        Assertions.assertThat(updatedUser)
                .isNotNull();
    }

}