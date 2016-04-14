/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.sistemabibliotecautfpr;

/**
 *
 * @author marco
 */
import javax.faces.bean.ManagedBean;

@ManagedBean
public class BasicView {

    private String text;

    public String getText() {
        System.out.println("oi");
        return text;
    }

    public void setText(String text) {
        this.text = text;
        System.out.println("oi");
    }
}
