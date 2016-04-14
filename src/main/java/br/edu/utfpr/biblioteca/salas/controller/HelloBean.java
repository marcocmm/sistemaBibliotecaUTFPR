package br.edu.utfpr.biblioteca.salas.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
@ManagedBean
@RequestScoped
public class HelloBean {
        public String getMessage(){
            return "Hello JSF + MAven!";
        }
    
}
