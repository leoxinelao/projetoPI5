package com.example.PI.entities;

import com.example.PI.entities.PlanoCarreira;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_metas")
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    
    private int quantidade;
    private String descricao;
    private String status;

    @ManyToOne
    @JoinColumn(name = "plano_carreira_id")
    @JsonIgnore
    private PlanoCarreira planoCarreira;
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PlanoCarreira getPlanoCarreira() {
        return planoCarreira;
    }

    public void setPlanoCarreira(PlanoCarreira planoCarreira) {
        this.planoCarreira = planoCarreira;
    }
}
