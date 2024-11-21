package com.example.PI.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_metas")
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Remova o @JsonIgnore aqui, pois queremos enviar o ID na resposta

    private int quantidade;
    private String descricao;
    private String status;

    @ManyToOne
    @JoinColumn(name = "plano_carreira_id", nullable = false)
    @JsonIgnore  // Se não quiser que a informação do plano de carreira seja serializada, mantenha o @JsonIgnore aqui
    private PlanoCarreira planoCarreira;

    // Getters e setters
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
