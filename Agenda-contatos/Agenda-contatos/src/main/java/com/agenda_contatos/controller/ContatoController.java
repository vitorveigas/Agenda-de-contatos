package com.agenda_contatos.controller;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agenda_contatos.model.Contato;
import com.agenda_contatos.services.ContatoServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoServices contatoServices;

    @PostMapping("/criarContato")
    public ResponseEntity<Contato> criarContato(@RequestBody Contato contato){
        // Salva o contato no banco
       contatoServices.salvarContato(contato);

        // Retorna o objeto salvo como JSON
        return ResponseEntity.ok(contato);
    }

    @GetMapping("/buscarNome")
public ResponseEntity<Contato> buscarPorNome(@RequestParam String nome) {
    Contato contato = contatoServices.buscarPorNome(nome);
    return ResponseEntity.ok(contato);
}


    @GetMapping("/listarContatos")
    public ResponseEntity<List<Contato>> listarContatos(){
        List<Contato> contatos = contatoServices.listarContatos();
        return ResponseEntity.ok(contatos);
    }

    @PutMapping("/atualizarContato")
    public ResponseEntity<Contato> atualizarPorNome(@RequestParam String nome, @RequestBody Contato contato){
        contatoServices.atualizarPorNome(nome, contato);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletarContato")
    public ResponseEntity<Void> deletarPorNome(@RequestParam String nome) {
        contatoServices.deleteByNome(nome);
        return ResponseEntity.noContent().build();
    }

}
