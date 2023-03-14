package br.gov.mt.sedec.controlesecretaria.controller;

import br.gov.mt.sedec.controlesecretaria.domain.acesso.AcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acesso")
@RequiredArgsConstructor
public class AcessoController {

    private final AcessoService acessoService;
    @GetMapping
    private ResponseEntity findAll(Pageable page) {
        return ResponseEntity.ok(acessoService.findAll(page));
    }
    @GetMapping("/{id}")
    private ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(acessoService.findById(id));
    }
}
