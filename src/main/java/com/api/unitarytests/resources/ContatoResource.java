/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.unitarytests.resources;

import com.api.unitarytests.models.Contato;
import com.api.unitarytests.repository.ContatoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Deivi
 */
@RestController
@RequestMapping("/api")
public class ContatoResource {
    
    @Autowired
    ContatoRepository contatoRepository;
    
    @GetMapping("/contatos")
    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }
    
    @GetMapping("/contato/{id}")
    public Contato listarContato(@PathVariable(value = "id") long id) {
        return contatoRepository.findById(id);
    }
    
    @PostMapping("/contato")
    public Contato adicionarContato(@RequestBody Contato contato) {
        return contatoRepository.save(contato);
    }
    
    @DeleteMapping("/contato")
    public void deletarContato(@RequestBody Contato contato) {
        contatoRepository.delete(contato);
    }
    
}
