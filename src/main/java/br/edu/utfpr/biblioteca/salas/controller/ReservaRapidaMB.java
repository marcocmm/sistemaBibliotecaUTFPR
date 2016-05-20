package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.bo.EstudanteBO;
import br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO;
import br.edu.utfpr.biblioteca.salas.view.BotaoHorario;
import br.edu.utfpr.biblioteca.salas.model.bo.SalaBO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import java.io.Serializable;

@Named(value = "reservaRapidaMB")
@ViewScoped
@ManagedBean
public class ReservaRapidaMB implements Serializable {

    private ReservaPO reserva;
    private String hora;

    private List<BotaoHorario> botoesHorario;

    //Formatadores de data-
    private final SimpleDateFormat formatoEmHoras;
    private final SimpleDateFormat formatoEmDia;

    public ReservaRapidaMB() {

        formatoEmHoras = new SimpleDateFormat("HH");
        formatoEmDia = new SimpleDateFormat("dd/MM/yyyy");
//        Aqui ficou com bug pq o método updateBotoesAtivosPorDia não retorna mais uma lista
//        Ver se é necessário retornar uma lista ou implematar o set do css dos botões no próprio método updateBotoesAtivosPorDia

        this.reserva = new ReservaPO(new EstudantePO(null, null, null, null), new SalaPO(0, true), new Date(), 0);

        this.botoesHorario = new ArrayList<>();
        botoesHorario.add(new BotaoHorario("8 as 9", "btn btn-success", true, 8));
        botoesHorario.add(new BotaoHorario("9 as 10", "btn btn-success", false, 8));
        botoesHorario.add(new BotaoHorario("10 as 11", "btn btn-success", true, 8));
    }

    public List<BotaoHorario> getBotoesHorario() {
        return botoesHorario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getDataAtual() {
        return new Date();
    }

    public ReservaPO getReserva() {
        return this.reserva;
    }

    /**
     * Método executado ao ser escolhida uma data no calendário
     *
     * @param event
     */
    public void onDateSelect(SelectEvent event) {
        Date data = (Date) event.getObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", formatoEmDia.format(event.getObject())));
    }

    /**
     * Este método solicita para a classe SalaBO uma lista de salas disponíveis
     * dado um dia e uma hora. Exibe em um Select<html> as salas
     *
     * @return
     */
    public List<SalaPO> getSalasDisponiveis() {
        return SalaBO.getSalasDisponiveis(this.reserva.getDataInicial());
    }

    /**
     * Responsável pelo wizard. Cada clique em next executa este método.
     *
     * @param event
     * @return
     */
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");

    }

    /**
     *
     * @return
     */
    public void reservarSala() {
        if (EstudanteBO.autenticar(this.reserva.getEstudante().getRa(), this.reserva.getEstudante().getSenha())) {
            FacesMessage msg = new FacesMessage("Credenciais inválidas");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        Date dataInicial = CalendarioHelper.parseDateTime(this.reserva.getStrDataInicial(), this.hora);
        this.reserva.setDataInicial(dataInicial);

        if (reserva.getDataFinal().equals(reserva.getDataInicial())) {
            FacesMessage msg = new FacesMessage("Data Final igual data inicial");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if (reserva.getDataFinal().before(reserva.getDataInicial())) {
            FacesMessage msg = new FacesMessage("Data Final deve ser posterior à data inicial");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        EstudantePO estudante;
        estudante = EstudanteBO.obterEstudante(this.reserva.getEstudante().getRa());
        this.reserva.setEstudante(estudante);

        SalaPO sala = SalaBO.obter(this.reserva.getSala().getId());
        this.reserva.setSala(sala);

        boolean canReservar = ReservaBO.reservar(reserva);

        if (!canReservar) {
            FacesMessage msg = new FacesMessage("Fail", "Welcome :" + getReserva().getEstudante().getRa());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "Welcome :" + getReserva().getEstudante().getRa());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Este método deve solicitar para a classe SalaBO um hash contendo as salas
     * que possuem horários disponíveis dado um dia. Ele é quem deve setar o css
     * dos botões.
     */
    public void updateBotoesAtivosPorDia() {

        //hash com key inteiro (hora -> 8, 9, 10...) e boolean se existe alguma sala disponível ou todas estão reservas.
        HashMap<Integer, Boolean> salasDisponiveis = SalaBO.getStatusDaSala(this.reserva.getDataInicial());

        //chamar método alteraEstilo ou implementar o método aqui
    }

    public void alterarEstilo() {
        String parametroUmAtivo = "";
        if (parametroUmAtivo.equals("btn btn-success")) {
            parametroUmAtivo = "ui-priority-primary";
        } else {
            parametroUmAtivo = "btn btn-success";
        }
    }

    public void cancelarReserva() {
        throw new UnsupportedOperationException();
    }

    public void ativarReserva() {
        throw new UnsupportedOperationException();
    }

    public List<ReservaPO> listarReservas(EstudantePO estudante) {
        throw new UnsupportedOperationException();
    }

}
