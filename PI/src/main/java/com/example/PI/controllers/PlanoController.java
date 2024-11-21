package com.example.PI.controllers;

import com.example.PI.entities.Meta;
import com.example.PI.entities.PlanoCarreira;
import com.example.PI.repositories.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping(value = "/planos")
public class PlanoController {

    @Autowired
    private PlanoRepository repository;

    @PostMapping
    public ResponseEntity<PlanoCarreira> criarPlanoComMetas(@RequestBody PlanoCarreira planoCarreira) {
    // Depuração: Verificar se as metas estão chegando
    if (planoCarreira.getMetas() != null) {
        for (Meta meta : planoCarreira.getMetas()) {
            System.out.println("Meta: " + meta.getDescricao() + ", Quantidade: " + meta.getQuantidade());
            meta.setPlanoCarreira(planoCarreira);  // Garantir que as metas estão associadas ao plano
        }
    }

    PlanoCarreira planoCriado = repository.save(planoCarreira);
    return ResponseEntity.status(HttpStatus.CREATED).body(planoCriado);
}

    @GetMapping
    public List<PlanoCarreira> ListarTodos() {
        List<PlanoCarreira> result = repository.findAll();
        return result;
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
                plano.getMetas().clear();
                for (Meta meta : planoAtualizado.getMetas()) {
                    meta.setPlanoCarreira(plano);
                    plano.getMetas().add(meta);
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
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
     @GetMapping(value = "/usuario/{userId}")
    public ResponseEntity<PlanoCarreira> listarPlanoPorUsuario(@PathVariable Long userId) {
        Optional<PlanoCarreira> plano = repository.findByFuncionarioId(userId);

        if (plano.isPresent()) {
            return ResponseEntity.ok(plano.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 caso não exista
        }
    }
    
}
