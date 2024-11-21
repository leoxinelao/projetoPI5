package com.example.PI.controllers;

import com.example.PI.entities.LoginDTO;
import com.example.PI.entities.User;
import com.example.PI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 *
 * @author eee
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginDTO loginDTO) {
        Optional<User> usuarioOpt = usuarioRepository.findByEmail(loginDTO.getEmail());

        if (usuarioOpt.isPresent()) {
            User usuario = usuarioOpt.get();
            if (usuario.getSenha().equals(loginDTO.getSenha())) {
                // Retorna o login bem-sucedido com email, classe e id do usuário
                LoginResponse response = new LoginResponse(usuario.getEmail(), usuario.getClasse(), usuario.getId());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }
    }

    // Classe de resposta para login com os dados do usuário
    public static class LoginResponse {
        private String email;
        private String classe;
        private Long id; // ID do usuário

        public LoginResponse(String email, String classe, Long id) {
            this.email = email;
            this.classe = classe;
            this.id = id; // Definir o ID
        }

        // Getters e Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getClasse() {
            return classe;
        }

        public void setClasse(String classe) {
            this.classe = classe;
        }

        public Long getId() {
            return id; // Método para retornar o ID
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
