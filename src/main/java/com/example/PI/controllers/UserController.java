/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.PI.controllers;

import com.example.PI.entities.PlanoCarreira;
import com.example.PI.entities.User;
import com.example.PI.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping(value="/{id}")
    public User ListarId(@PathVariable Long id){
        User result = repository.findById(id).get();
        return result;
    }
    
    @PostMapping
    public User cadastroUsuario(@RequestBody User user){
        User result = repository.save(user);
        return result;
    }
    
}
