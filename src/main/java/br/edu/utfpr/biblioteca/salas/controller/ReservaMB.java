/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

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

@Named(value = "reservaMB")
@ViewScoped
@ManagedBean
public class ReservaMB {

    private Reserva reserva;
    private Date date;
    private String[][] parametrosBotoes;
    //Formatadores de data
    SimpleDateFormat formartoEmHoras;
    SimpleDateFormat formatoEmDia;
    //Tipos dos botoes
    String parametroUmAtivo;
    String parametroUmDesativado;
    String parametroDoisAtivo;
    String parametroDoisDesativado;

    @ViewScoped
    private List<Integer> horariosReserva;

    private static ReservaMB instancia;

    /**
     * Creates a new instance of ReservaMB
     */
    public ReservaMB() {
//    private ReservaMB() {
        formartoEmHoras = new SimpleDateFormat("HH:mm:ss");
        formatoEmDia = new SimpleDateFormat("dd/MM/yyyy");

        parametroUmAtivo = "btn btn-success";
        parametroUmDesativado = "btn btn-danger";
        parametroDoisAtivo = "false";
        parametroDoisDesativado = "true";
        date = new Date();
        parametrosBotoes = new String[14][2];
        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(date), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);

        horariosReserva = new ArrayList<>();
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public boolean salvarReserva(Reserva reserva) {
        if (!EstudanteMB.isAutentico(reserva.getEstudante().getRa(), reserva.getEstudante().getSenha())) {
            return false;
        }
        if (reserva.getDataFinal().equals(reserva.getDataInicial())) {
            return false;
        }
        if (reserva.getDataFinal().before(reserva.getDataInicial())) {
            return false;
        }
        ReservaDAO dao = new ReservaDAO();
        return dao.insert(reserva);
    }

    public void onDateSelect(SelectEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", formatoEmDia.format(event.getObject())));

        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(date), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);

        //teste
//        System.out.println("data: " + getDate1());
        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(date), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);

    }

    public List<String> getHorasAtivasPorDia(Date date) {

        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> listaTodasReservas = reservaDAO.list();
        List<String> listaReservasAtivasPorDia = new ArrayList<>();
        String diaProcurado = formatoEmDia.format(date);
        String diaAtivo;
        for (Reserva reserva : listaTodasReservas) {
            diaAtivo = formatoEmDia.format(reserva.getDataInicial());
            if (diaProcurado.equals(diaAtivo)) {
                listaReservasAtivasPorDia.add(formartoEmHoras.format(reserva.getDataInicial()).substring(0, 2));
            }
        }
        return listaReservasAtivasPorDia;
    }

//    public List<ReservaAtiva> getReservasPorDia(Date date) {
//
//        ReservaAtivaDAO reservaAtivaDAO = new ReservaAtivaDAO();
//        List<ReservaAtiva> listaTodasReservasAtivas = reservaAtivaDAO.list();
//        List<ReservaAtiva> listaReservasAtivasPorDia = new ArrayList<>();
//        String diaProcurado = formatoEmDia.format(date);
//        String diaAtivo;
//        for (ReservaAtiva reservaAtiva : listaTodasReservasAtivas) {
//            diaAtivo = formatoEmDia.format(reservaAtiva.getData());
//            if (diaProcurado.equals(diaAtivo)) {
//                listaReservasAtivasPorDia.add(reservaAtiva);
//            }
//        }
//        return listaReservasAtivasPorDia;
//    }
//
    public String[][] getParametrosBotoes(List<String> horasAtivas, String parametroUmAtivo,
            String parametroUmDesativado, String parametroDoisAtivo, String parametroDoisDesativado) {

        String hora;
        String[][] parametrosBotoes = new String[14][2];
        for (int i = 8; i < 22; i++) {
            hora = "";
            if (i < 10) {
                hora = "0";
            }
            hora += i;
            if (horasAtivas.contains(hora) || date.after(new Date())) {
                parametrosBotoes[(i - 8)][0] = parametroUmDesativado;
                parametrosBotoes[(i - 8)][1] = parametroDoisDesativado;
            } else {
                parametrosBotoes[(i - 8)][0] = parametroUmAtivo;
                parametrosBotoes[(i - 8)][1] = parametroDoisAtivo;
            }
        }
        return parametrosBotoes;
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");

    }

    public Date getDate1() {
        return date;
    }

    public void setDate1(Date date1) {
        this.date = date1;
    }

    public String[][] getParametrosBotoes() {
        return parametrosBotoes;
    }

    public void setHoraInicial(int hora) {
        if (horariosReserva.size() <= 2) {
            this.horariosReserva.add(hora);
            System.out.println("Setou hora: " + hora);
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Limite de reserva de duas horas atingido", null));
        }
    }

//    public static synchronized ReservaMB getInstance(){
//        if (instancia == null){
//            instancia = new ReservaMB();
//        }
//        return instancia;
//    }
}
