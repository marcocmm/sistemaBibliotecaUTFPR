/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.dao.EstudanteDAO;
import tools.CalendarioHelper;
import br.edu.utfpr.biblioteca.salas.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.Estudante;
import br.edu.utfpr.biblioteca.salas.model.Reserva;
import br.edu.utfpr.biblioteca.salas.model.Sala;
import br.edu.utfpr.biblioteca.salas.model.Status;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import java.io.Serializable;

@Named(value = "reservaMB")
@ViewScoped
@ManagedBean
public class ReservaMB implements Serializable {

    private StatusBotao statusBotao;
    private Reserva reserva;
    private String strDataInicial;
    private String strHorario;

    private Integer sala;
    private List<Integer> salasOcupadas;
    private Date date;

    //Hora do botão selecionado
    private String horaSelecionada;
    //Formatadores de data
    private final SimpleDateFormat formartoEmHoras;
    private final SimpleDateFormat formatoEmDia;

    private List<Integer> horariosReserva;

    public ReservaMB() {
        Estudante estudante = new Estudante(null, null, null, null);
        reserva = new Reserva(estudante, new Sala(1, true), new Date(), 0);

        formartoEmHoras = new SimpleDateFormat("HH");
        formatoEmDia = new SimpleDateFormat("dd/MM/yyyy");
        date = new Date();

//        parametrosBotoes = new String[14][2];
//        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(date), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);

        date = new Date();
        statusBotao = new StatusBotao();
        statusBotao.setParametrosBotoes(getHorasAtivasPorDia(date), date);
        horariosReserva = new ArrayList<>();
    }

    public void save() {
        if (!EstudanteMB.isAutentico(this.reserva.getEstudante().getRa(), this.reserva.getEstudante().getSenha())) {
            FacesMessage msg = new FacesMessage("Credenciais inválidas", "Welcome :" + getReserva().getEstudante().getRa());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        EstudanteDAO estudanteDAO = new EstudanteDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        SalaDAO salaDAO = new SalaDAO();

        Date dataInicial = CalendarioHelper.parseDateTime(this.strDataInicial, this.strHorario);
        this.reserva.setDataInicial(dataInicial);

        Estudante estudante = estudanteDAO.obter(this.reserva.getEstudante().getRa());
        this.reserva.setEstudante(estudante);

        Sala sala = salaDAO.obter(this.reserva.getSala().getId());
        this.reserva.setSala(sala);

        if (reservaDAO.insert(reserva)) {
            FacesMessage msg = new FacesMessage("Successful", "Welcome :" + getReserva().getEstudante().getRa());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        FacesMessage msg = new FacesMessage("Fail", "Welcome :" + getReserva().getEstudante().getRa());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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
        Date data = (Date) event.getObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", formatoEmDia.format(event.getObject())));

//        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(statusBotao.getDate1()), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);
        System.out.println("data: " + getDate());
//        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(date), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);

    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");

    }
    private static HashMap<Sala, Reserva> clone(HashMap<Sala, Reserva> map) {
        HashMap<Sala, Reserva> copy = new HashMap();
        Iterator<Entry<Sala, Reserva>> iterator = map.entrySet().iterator();
        Entry<Sala, Reserva> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            copy.put((Sala) entry.getKey().clone(), (Reserva) entry.getValue().clone());
        }
        return copy;
    }

    /**
     * Método descreve um dia
     * @param date uma data válida
     * @return um hashmap que associa data ao conjunto de salas, cada qual com sua reserva
     */
    public static HashMap<Date, HashMap<Sala, Reserva>> descreverDia(Date date) {
        ReservaDAO dao = new ReservaDAO();
        SalaDAO salaDAO = new SalaDAO();

        List<Sala> salas = salaDAO.list();
        List<Date> horarios = CalendarioHelper.getHorarios(date);
        List<Reserva> reservas = dao.listByDate(date);

        HashMap<Sala, Reserva> salaTemReservas = new HashMap();
        HashMap<Date, HashMap<Sala, Reserva>> dataTemReservas = new HashMap();

        for (Sala sala : salas) {
            salaTemReservas.put(sala, null);
        }

        for (Date horario : horarios) {
            dataTemReservas.put(horario, clone(salaTemReservas));
        }

        for (Reserva reserva : reservas) {
            dataTemReservas.get(reserva.getDataInicial()).put(reserva.getSala(), reserva);
        }
        return dataTemReservas;
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

//    public void alteraEstilo() {
//        if (parametroUmAtivo.equals("btn btn-success")) {
//            parametroUmAtivo = "ui-priority-primary";
//        } else {
//            parametroUmAtivo = "btn btn-success";
//        }
//    }
//
//    public String getParametroUmAtivo() {
//        return parametroUmAtivo;
// public List<String> getHorasAtivasPorDia(){
//       return listaReservasAtivasPorDia; 
//    }  
//    public static synchronized ReservaMB getInstance(){
//        if (instancia == null){
//            instancia = new ReservaMB();
//        }
//        return instancia;
//    }
    public List<String> getHorasAtivasPorDia(Date date) {

        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> listaTodasReservas = reservaDAO.list();
        List<String> listaReservasAtivasPorDia = new ArrayList<>();
        String diaProcurado = formatoEmDia.format(date);
        String diaAtivo;
        salasOcupadas = new ArrayList<>();

        for (Reserva reserva : listaTodasReservas) {
            diaAtivo = formatoEmDia.format(reserva.getDataInicial());
            if (diaProcurado.equals(diaAtivo)) {
                if (reserva.getStatus().equals(new Status("ativa"))) {
                    listaReservasAtivasPorDia.add(formartoEmHoras.format(reserva.getDataInicial()).substring(0, 2));
                }
                salasOcupadas.add(reserva.getId());
            }
        }

        return listaReservasAtivasPorDia;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setHoraInicial(int hora) {
        if (horariosReserva.size() < 2) {
            this.horariosReserva.add(hora);
            System.out.println("Setou hora: " + hora);
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Limite de reserva de duas horas atingido", null));
        }
    }

    public String getStrHorario() {
        return strHorario;
    }

    public void setStrHorario(String strHorario) {
        this.strHorario = strHorario;
    }

    public String getStrDataInicial() {
        return strDataInicial;
    }

    public void setStrDataInicial(String strDataInicial) {
        this.strDataInicial = strDataInicial;
    }

    public List<Integer> getSalasDisponiveis() {
        List<Integer> listaSalasDisponiveis = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            if (!(salasOcupadas.contains(i))) {
                listaSalasDisponiveis.add(i);
            }
        }
        return listaSalasDisponiveis;
    }

// public List<String> getHorasAtivasPorDia(){
//       return listaReservasAtivasPorDia; 
//    }  
//    public static synchronized ReservaMB getInstance(){
//        if (instancia == null){
//            instancia = new ReservaMB();
//        }
//        return instancia;
//    }
    public void setSala(int sala) {
        this.sala = sala;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date1) {
        this.date = date1;
    }

    public SimpleDateFormat getFormatoEmDia() {
        return formatoEmDia;
    }

    public void setHoraSelecionada(String horaSelecionada) {
        this.horaSelecionada = horaSelecionada;
    }

    public StatusBotao getStatusBotao() {
        return statusBotao;
    }
}
