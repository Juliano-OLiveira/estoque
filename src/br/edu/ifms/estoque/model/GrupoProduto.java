/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

/**
 *
 * @author santos
 */
public class GrupoProduto {
    private Long id;
    private String nome;
    private GrupoProduto subgrupo;

    public GrupoProduto() {
    }

    public GrupoProduto(Long id, String nome, GrupoProduto subgrupo) {
        this.id = id;
        this.nome = nome;
        this.subgrupo = subgrupo;
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

    public GrupoProduto getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(GrupoProduto subgrupo) {
        this.subgrupo = subgrupo;
    }
    
    public Object[] toArray() {
        Object[] array = new Object[3];
        array[0] = this.id;
        array[1] = this.nome;
        array[2] = this.subgrupo;
        return array;
    }
}
