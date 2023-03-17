package br.gov.mt.sedec.controlesecretaria.controller;

import br.gov.mt.sedec.controlesecretaria.domain.myUser.*;
import br.gov.mt.sedec.controlesecretaria.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final MyUserService myUserService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/user")
    @Transactional
    public ResponseEntity persistUser(@RequestBody @Valid NewUserDto newUserDto, UriComponentsBuilder uriBuilder) {
        MyUser user = myUserService.persistUser(newUserDto);
        var uri = uriBuilder.path("/auth/user/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(user.toUserDto());
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDto userLoginDto) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.username(), userLoginDto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var tokenJWT = tokenService.generateToken((MyUser) authentication.getPrincipal());

        return ResponseEntity.ok(tokenJWT);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity userDetail(@PathVariable Long id) {
        MyUser user = myUserService.findByIdOrElseThrowBadRequest(id);
        return ResponseEntity.ok(user.toUserDto());
    }

    @PatchMapping(path = "/user")
    public ResponseEntity updatePassword(@RequestBody @Valid UpdateUserPasswordDto dto) {

        myUserService.updateUserPassword(dto);

        return ResponseEntity.noContent()
                .build();
    }

}
