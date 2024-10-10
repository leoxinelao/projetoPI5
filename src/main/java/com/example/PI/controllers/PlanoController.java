/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.PI.controllers;

import com.example.PI.entities.Meta;
import com.example.PI.entities.PlanoCarreira;
import com.example.PI.repositories.PlanoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author eee
 */
@RestController
@RequestMapping(value="/planos")
public class PlanoController {
    
    @Autowired
    private PlanoRepository repository;
 
    
    @GetMapping
    public List<PlanoCarreira> ListarTodos(){
        List<PlanoCarreira> result = repository.findAll();
        return result;
    }
    
    @PostMapping
    public ResponseEntity<PlanoCarreira> criarPlanoComMetas(@RequestBody PlanoCarreira planoCarreira) {
        for (Meta meta : planoCarreira.getMetas()) {
            meta.setPlanoCarreira(planoCarreira);
        }
        PlanoCarreira planoCriado = repository.save(planoCarreira);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoCriado);
    }

    
    public List<PlanoCarreira> listarTodosPlanos() {
        return repository.findAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PlanoCarreira> atualizarPlano(@PathVariable Long id, @RequestBody PlanoCarreira planoAtualizado) {

    Optional<PlanoCarreira> planoExistente = repository.findById(id);

    if (planoExistente.isPresent()) {
        PlanoCarreira plano = planoExistente.get();
        
        // Atualiza os campos simples
        plano.setNome(planoAtualizado.getNome());
        plano.setCargoAtual(planoAtualizado.getCargoAtual());
        plano.setBonificacao(planoAtualizado.getBonificacao());
        plano.setFuncionario(planoAtualizado.getFuncionario());

        // Atualiza as metas
        if (planoAtualizado.getMetas() != null) {
            // Limpa as metas existentes
            plano.getMetas().clear();

            // Associa cada meta ao plano
            for (Meta meta : planoAtualizado.getMetas()) {
                meta.setPlanoCarreira(plano);  // Faz a associação entre meta e plano
                plano.getMetas().add(meta);    // Adiciona ao plano
            }
        }

        PlanoCarreira planoSalvo = repository.save(plano);
        return ResponseEntity.ok(planoSalvo);
    } else {
        return ResponseEntity.notFound().build();
    }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
    Optional<PlanoCarreira> planoExistente = repository.findById(id);
    
    if (planoExistente.isPresent()) {
        repository.delete(planoExistente.get());
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    } else {
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o plano não existir
    }
}
    

}


    

