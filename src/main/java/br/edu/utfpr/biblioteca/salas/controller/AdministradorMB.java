/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.dao.AdministradorDAO;
import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import tools.CalendarioHelper;
import br.edu.utfpr.biblioteca.salas.model.entity.AdministradorPO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import tools.ReservasHorario;

@Named(value = "administradorMB")
@ViewScoped
@ManagedBean
public class AdministradorMB {

    private AdministradorPO administrador;
    private final AdministradorDAO administradorDAO;

    private Date data;
    private Dia diaSelecionado;
    private int idSala;
    private String strSala;
    private String strHorario;
    private String strStatus;

    /**
     * Creates a new instance of AdministradorMB
     */
    public AdministradorMB() {
        this.strSala = "Sala";
        this.administradorDAO = new AdministradorDAO();
    }

    public AdministradorPO getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorPO administrador) {
        this.administrador = administrador;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Dia getDiaSelecionado() {
        return diaSelecionado;
    }

    public void setDiaSelecionado(Dia diaSelecionado) {
        this.diaSelecionado = diaSelecionado;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getStrHorario() {
        return strHorario;
    }

    public void setStrHorario(String strHorario) {
        this.strHorario = strHorario;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String getStrSala() {
        return strSala;
    }

    public void setStrSala(String strSala) {
        this.strSala = strSala;
    }

    public List<Date> getCalendario() {
        return CalendarioHelper.getCalendario(new Date());
    }

    public List<Dia> getMes(Date date) {
        List<Date> dias = CalendarioHelper.getCalendario(date);
        List<Dia> mes = new ArrayList<>();
        for (Date dia : dias) {
            mes.add(descreverDia(dia));
        }
        return mes;
    }

    public List<Dia> getMes() {
        return getMes(new Date());
    }

    public List<ReservasHorario> getReservasHorario() {
        SalaDAO salaDAO = new SalaDAO();
        SalaPO sala = salaDAO.obter(this.idSala);
        List<ReservasHorario> reservasHorario = new ArrayList();
        for (ReservaPO reserva : ReservaMB.getReservas(this.data, sala)) {
            ReservasHorario rH = new ReservasHorario();
            rH.setHorario(String.valueOf(CalendarioHelper.getDatabaseDateFormat(reserva.getDataInicial())));
            rH.setStatus(String.valueOf(reserva.getStatus().getName()));
            reservasHorario.add(rH);
        }

        return reservasHorario;
    }

    public Dia descreverDia(Date date) {
        HashMap<Date, HashMap<SalaPO, ReservaPO>> dataTemReservas = ReservaMB.descreverDia(date);

        Dia dia;
        Hora horario;

        dia = new Dia();
        dia.setData(date);

        for (Map.Entry<Date, HashMap<SalaPO, ReservaPO>> horaTemReserva : dataTemReservas.entrySet()) {
            Date hora = horaTemReserva.getKey();
            HashMap<SalaPO, ReservaPO> salaTemReservas = horaTemReserva.getValue();

            horario = new Hora();
            horario.setHora(hora);
            for (Map.Entry<SalaPO, ReservaPO> salaTemReserva : salaTemReservas.entrySet()) {
                ReservaPO reserva = salaTemReserva.getValue();

                horario.addReserva(reserva);
            }
            dia.addHora(horario);
        }
        return dia;
    }

    public void gerarGraficos() {
        throw new UnsupportedOperationException();
    }

    public List<ReservaPO> obterRelatorio(Date dataInicial, Date dataFinal) {
        throw new UnsupportedOperationException();
    }
}
