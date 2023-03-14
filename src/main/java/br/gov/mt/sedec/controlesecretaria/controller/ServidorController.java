package br.gov.mt.sedec.controlesecretaria.controller;

import br.gov.mt.sedec.controlesecretaria.domain.servidor.ServidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servidor")
@RequiredArgsConstructor
public class ServidorController {
    private final ServidorService servidorService;
    @GetMapping
    private ResponseEntity findAll(Pageable page){
        return ResponseEntity.ok(servidorService.findAll(page));
    }
    @GetMapping(path = "/{id}")
    private ResponseEntity findById(@PathVariable Long id){
        return ResponseEntity.ok(servidorService.findById(id));
    }
}
