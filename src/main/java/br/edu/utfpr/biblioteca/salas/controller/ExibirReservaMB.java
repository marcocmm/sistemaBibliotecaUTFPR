/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Hora;
import br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author marco
 */
@Named(value = "exibirReservaMB")
@ViewScoped
@ManagedBean
public class ExibirReservaMB {

    private ReservaPO reserva;
    private Hora hora;
//    private List<ReservaPO> reservas;
    private CalendarioMB calendario;
    private int idReserva;
    private LoginMB sessionLogin = new LoginMB();
    private EstudantePO estudante = sessionLogin.getEstudante();


    /**
     * Creates a new instance of SalaMB
     */
    public ExibirReservaMB() {
        this.reserva = new ReservaPO(new EstudantePO(null, null, null, null), new SalaPO(0, true), new Date(), 0);
    }
    
//    public String getNome(int index){
//       reservas.get(index).getEstudante().getNome();
//            
//       
//        
//            return reservas.get(index).getEstudante().getNome();
//        
//    }

    public ReservaPO getReserva() {
        return reserva;
    }

    public String getDiaMesReserva(){
        return CalendarioHelper.getDiaMesAno(reserva.getDataInicial());
    }
    
    public CalendarioMB getCalendario() {
        return calendario;

    }
    
    public String canCancelReserva(){
        if (reserva.getEstudante().getRa().equals(estudante.getRa())){
            return "true";
        }
        return "false";
    }

    

    public void cancelarReserva() {
        FacesMessage msg;
        if (ReservaBO.setStatus(reserva, "inativa")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reserva Cancelada", getReserva().getStrDataInicial());
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Reserva não pôde ser cancelada!", getReserva().getStrDataInicial());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

}
