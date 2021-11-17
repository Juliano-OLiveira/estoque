/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque.model;

/**
 *
 * @author santos
 */
public class Marca {
    private Long id;
    private String nome;

    public Marca() {
    }

    public Marca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
    public Object[] toArray() {
        Object[] array = new Object[2];
        array[0] = this.id;
        array[1] = this.nome;
        return array;
    }
}
