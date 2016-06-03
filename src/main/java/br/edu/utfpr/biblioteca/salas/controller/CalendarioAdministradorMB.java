package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Dia;
import br.edu.utfpr.biblioteca.salas.model.ReservasHorario;
import br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO;
import br.edu.utfpr.biblioteca.salas.model.bo.SalaBO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import br.edu.utfpr.biblioteca.salas.model.entity.AdministradorPO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@Named(value = "administradorMB")
@ViewScoped
@ManagedBean
public class CalendarioAdministradorMB {

    private AdministradorPO administrador;

    private Date dataSelecionada;
    private Dia diaSelecionado;

    private SalaPO sala;
    private String strSala;
    private String strHorario;
    private String strStatus;

    /**
     * Creates a new instance of AdministradorMB
     */
    public CalendarioAdministradorMB() {
        this.strSala = "Sala";
        this.sala = new SalaPO(1, true);
        this.administrador = new AdministradorPO(null, null);
    }

    public AdministradorPO getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorPO administrador) {
        this.administrador = administrador;
    }

    public Date getDataSelecionada() {
        return dataSelecionada;
    }

    public void setDataSelecionada(Date dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
    }

    public Dia getDiaSelecionado() {
        return diaSelecionado;
    }

    public void setDiaSelecionado(Dia diaSelecionado) {
        this.diaSelecionado = diaSelecionado;
    }

    public SalaPO getSala() {
        return sala;
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

    /**
     * Obtém HashMap com lista de salas
     *
     * @return
     */
    public HashMap<String, String> getSalas() {
        return SalaBO.getSalas();
    }

    public List<ReservasHorario> getReservasHorario() {
        return SalaBO.getReservasHorario(this.sala);
    }

    /**
     * Obtém uma lista com os dias do mês incluindo mês anterior, atual e
     * posterior. Fecha um calendário.
     *
     * @return
     */
    public List<Date> getCalendario() {
        return CalendarioHelper.getCalendario(new Date());
    }

    /**
     * Obtém uma lista com todos os dias do mês dado. Cada dia é uma relação de
     * 14 horas cada qual com no máximo 6 reservas
     *
     * @param date
     * @return
     */
    public List<Dia> getMes(Date date) {
        return ReservaBO.descreverMes(date);
    }

    /**
     * Obtém lista com todos os dias do mês atual. Cada dia é uma relação de 14
     * horas cada qual com no máximo 6 reservas
     *
     * @return
     */
    public List<Dia> getMes() {
        return getMes(new Date());
    }

    public void gerarGraficos() {
        throw new UnsupportedOperationException();
    }

    public List<ReservaPO> obterRelatorio(Date dataInicial, Date dataFinal) {
        throw new UnsupportedOperationException();
    }
}
