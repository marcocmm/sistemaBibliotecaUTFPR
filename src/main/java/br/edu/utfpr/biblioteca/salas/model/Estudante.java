/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

/**
 *
 * @author RAZIEL
 */
public class Estudante {
String ra;
String nome;
String senha;
String email;

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
    
   public String getNome() {
        return nome;
    }       
    public void setNome(String nome) {
        this.nome = nome;        
        
    }    
     public String getSenha() {
        return senha;
    }       
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
     public String getEmail() {
        return email;
    }       
    public void setEmail(String email) {
        this.email = email;
    }

    
}
