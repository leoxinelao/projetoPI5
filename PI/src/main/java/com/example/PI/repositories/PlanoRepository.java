/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.PI.repositories;

import com.example.PI.entities.PlanoCarreira;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eee
 */
public interface PlanoRepository extends JpaRepository<PlanoCarreira, Long>{
    
}
