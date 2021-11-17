/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

/**
 *
 * @author santos
 */
public class UnidadeMedida {
    private Long id;
    private String nome;
    private boolean fracionado;

    public UnidadeMedida() {
    }

    public UnidadeMedida(Long id, String nome, boolean fracionado) {
        this.id = id;
        this.nome = nome;
        this.fracionado = fracionado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnidadeMedida(boolean fracionado) {
        this.fracionado = fracionado;
    }
    
    
}
