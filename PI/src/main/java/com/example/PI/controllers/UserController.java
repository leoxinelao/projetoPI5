/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.PI.controllers;

import com.example.PI.entities.PlanoCarreira;
import com.example.PI.entities.User;
import com.example.PI.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eee
 */
@RestController
@RequestMapping(value="/users")
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    @GetMapping
    public List<User> ListarTodos(){
        List<User> result = repository.findAll();
        return result;
    }
    
    @GetMapping(value = "/{id}")
public ResponseEntity<User> listarPorId(@PathVariable Long id) {
    Optional<User> result = repository.findById(id);
    if (result.isPresent()) {
        return ResponseEntity.ok(result.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se o ID não existir
    }
}
    
     @PostMapping
    public ResponseEntity<User> cadastrarUsuario(@RequestBody User usuario) {
        // Verifique se o CPF ou e-mail já existem no banco de dados, se necessário
        Optional<User> usuarioExistente = repository.findByCpfOrEmail(usuario.getCpf(), usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Conflito se usuário já existe
        }

        // Salvar o novo usuário
        User novoUsuario = repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
    Optional<User> userExistente = repository.findById(id);
    
    if (userExistente.isPresent()) {
        repository.delete(userExistente.get());
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    } else {
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o usuario não existir
    }
}
    
}
