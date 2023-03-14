package br.gov.mt.sedec.controlesecretaria.controller;

import br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso.RequerimentoDeAcessoDtoIncluir;
import br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso.RequerimentoDeAcessoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/requerimento-de-acesso")
@RestController
@RequiredArgsConstructor
public class RequerimentoDeAcessoController {

    private final RequerimentoDeAcessoService requerimentoDeAcessoService;
    @PostMapping
    public ResponseEntity persistNewRequerimentoDeAcesso(@RequestBody @Valid RequerimentoDeAcessoDtoIncluir requerimento){

        return ResponseEntity.ok(requerimentoDeAcessoService.persist(requerimento));
    }
    @GetMapping
    private ResponseEntity findAll(Pageable page){
        return ResponseEntity.ok(requerimentoDeAcessoService.findAll(page));
    }
}
