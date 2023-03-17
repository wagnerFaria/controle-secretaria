package br.gov.mt.sedec.controlesecretaria.domain.myUser;

import br.gov.mt.sedec.controlesecretaria.domain.myRole.MyRole;
import br.gov.mt.sedec.controlesecretaria.domain.myRole.MyRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final MyUserRepository repository;
    private final MyRoleService myRoleService;
    private final PasswordEncoder passwordEncoder;

    public MyUser persistUser(NewUserDto dto) {
        MyRole roleUser = myRoleService.findByIdOrElseThrowBadRequest(1L);
        MyUser savedUser = repository.save(MyUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .roles(Arrays.asList(roleUser))
                .build());
        return savedUser;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }


    public MyUser findByIdOrElseThrowBadRequest(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Nenhum usuário com ID: " + id + " encontrado")
                );
    }

    public MyUser findByUsernameOrElseThrowBadRequest(String username) {
        MyUser user = repository.findMyUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, username + " não encontrado"));
        return user;
    }

    public MyUser updateUserPassword(UpdateUserPasswordDto dto) {

        MyUser user = this.findByUsernameOrElseThrowBadRequest(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName());

        user.setPassword(passwordEncoder.encode(dto.newPassword()));

        return repository.save(user);
    }
}
