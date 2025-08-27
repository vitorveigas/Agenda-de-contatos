package com.agenda_contatos.services;





import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda_contatos.model.Contato;
import com.agenda_contatos.repositories.ContatoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoServices {
    @Autowired
    private ContatoRepository contatoRepository;

    private List<Contato> contatos = new ArrayList<>();

    public void salvarContato(Contato contato) {
    
     contatoRepository.saveAndFlush(contato);
}

    public void deleteByNome(String nome) {
        contatoRepository.deleteByNome(nome);
    }

    public Contato buscarPorNome(String nome) {
        return contatoRepository.findByNome(nome).orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));
    }

    public void atualizarPorNome(String nome, Contato contato){
        Contato contatoAtual = buscarPorNome(nome);
        Contato contatoAtualizado = Contato.builder()
                .id(contatoAtual.getId())
                .nome(contato.getNome() != null ? contato.getNome() : contatoAtual.getNome())
                .email(contato.getEmail() != null ? contato.getEmail() : contatoAtual.getEmail())
                .telefone(contato.getTelefone() != null ? contato.getTelefone() : contatoAtual.getTelefone())
                .build();

        contatoRepository.saveAndFlush(contatoAtualizado);
    }

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

}
