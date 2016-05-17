package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Dia;
import br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO;
import tools.CalendarioHelper;
import br.edu.utfpr.biblioteca.salas.model.entity.AdministradorPO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@Named(value = "administradorMB")
@ViewScoped
@ManagedBean
public class AdministradorMB {

    private AdministradorPO administrador;

    private Date dataSelecionada;
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
        return ReservaBO.getMes(date);
    }

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
