/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;
import br.edu.utfpr.biblioteca.salas.controller.ReservaMB;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import br.edu.utfpr.biblioteca.salas.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.Reserva;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import br.edu.utfpr.biblioteca.salas.controller.StatusBotao;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;


@Named(value = "statusBotoes")
@ViewScoped
@ManagedBean

public class StatusBotao {
    ReservaMB reservaMB;
    
    private final String parametroUmAtivo;
    private final String parametroUmDesativado;
    private final String parametroDoisAtivo;
    private final String parametroDoisDesativado;
    private final String parametroUmSelecionado;    
    private String[][] parametrosBotoes;
    
    public StatusBotao(){
    
        parametroUmAtivo = "btn btn-success";
        parametroUmDesativado = "btn btn-danger";
        parametroDoisAtivo = "false";
        parametroDoisDesativado = "true";
        parametroUmSelecionado = "btn btn";
        
        
        parametrosBotoes = new String[14][2];
        parametrosBotoes = getParametrosBotoes(reservaMB.getHorasAtivasPorDia(reservaMB.getDate1()), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);
        
    }

    
    
    public String[][] getParametrosBotoes(List<String> horasAtivas, String parametroUmAtivo,
            String parametroUmDesativado, String parametroDoisAtivo, String parametroDoisDesativado) {

        String hora;
        for (int i = 8; i < 22; i++) {
            hora = "";
            if (i < 10) {
                hora = "0";
            }
            hora += i;
            if (horasAtivas.contains(hora) || reservaMB.getDate1().after(new Date())) {
                parametrosBotoes[(i - 8)][0] = parametroUmDesativado;
                parametrosBotoes[(i - 8)][1] = parametroDoisDesativado;
            } else {
                parametrosBotoes[(i - 8)][0] = parametroUmAtivo;
                parametrosBotoes[(i - 8)][1] = parametroDoisAtivo;
            }
        }
        return parametrosBotoes;
    }      
          
        
    
  
    public String[][] getParametrosBotoes(){
       return parametrosBotoes; 
    }  
          
          
        
        
        
}
    
    
    
    

