/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author marco
 */
@Named(value = "pessoaMB")
@ViewScoped
@ManagedBean
public class PessoaMB implements Serializable {

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoaLista = new ArrayList<Pessoa>();

    /**
     * Creates a new instance of PessoaMB
     */
    public PessoaMB() {
    }

    /**
     * Getters e Setters
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoaLista() {
        return pessoaLista;
    }

    public void setPessoaLista(List<Pessoa> pessoaLista) {
        this.pessoaLista = pessoaLista;
    }

    /**
     * Métodos
     */

    public void salvarPessoa() {

//adicionando pessoas a lista
        pessoaLista.add(pessoa);
//instanciado uma nova para ser cadastrada
        pessoa = new Pessoa();

    }
}
