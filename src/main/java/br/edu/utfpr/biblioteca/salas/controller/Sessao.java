/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Estudante;
  import javax.faces.context.ExternalContext;
  import javax.faces.context.FacesContext;
  import javax.servlet.http.HttpSession;
   
/**
 *
 * @author mateus
 */
public class Sessao {
      
      private static Sessao instancia;
      
      public static Sessao getInstancia(){
           if (instancia == null){
               instancia = new Sessao();
           }
           
           return instancia;
      }
      
      private Sessao(){
           
      }
      
      private ExternalContext currentExternalContext(){
           if (FacesContext.getCurrentInstance() == null){
               throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
           }else{
               return FacesContext.getCurrentInstance().getExternalContext();
           }
      }
      
      public Estudante getEstudanteLogado(){
           return (Estudante) getAtributo("estudanteLogado");
      }
      
      public void setUsuarioLogado(Estudante estudante){
           setAtributo("estudanteLogado", estudante);
      }
      
      public void encerrarSessao(){   
           currentExternalContext().invalidateSession();
      }
      
      public Object getAtributo(String nome){
           return currentExternalContext().getSessionMap().get(nome);
      }
      
      public void setAtributo(String nome, Object valor){
           currentExternalContext().getSessionMap().put(nome, valor);
      }
      
  }

