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

    private Reserva reserva;
    private final ReservaDAO reservaDAO;

    private StatusBotao statusBotao;

    private String strDataInicial;
    private String strHorario;
    private List<Integer> idSalasOcupadas;
    private List<Integer> horariosReserva;
    private Date date;

    //Formatadores de data
    private final SimpleDateFormat formatoEmHoras;
    private final SimpleDateFormat formatoEmDia;

    public ReservaMB() {
        this.reservaDAO = new ReservaDAO();

        Estudante estudante = new Estudante(null, null, null, null);
        reserva = new Reserva(estudante, new Sala(1, true), new Date(), 0);

        formatoEmHoras = new SimpleDateFormat("HH");
        formatoEmDia = new SimpleDateFormat("dd/MM/yyyy");
        date = new Date();

        date = new Date();
        statusBotao = new StatusBotao();
        statusBotao.setParametrosBotoes(getHorasAtivasPorDia(date), date);
        horariosReserva = new ArrayList<>();
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public StatusBotao getStatusBotao() {
        return statusBotao;
    }

    public void setStatusBotao(StatusBotao statusBotao) {
        this.statusBotao = statusBotao;
    }

    public String getStrDataInicial() {
        return strDataInicial;
    }

    public void setStrDataInicial(String strDataInicial) {
        this.strDataInicial = strDataInicial;
    }

    public String getStrHorario() {
        return strHorario;
    }

    public void setStrHorario(String strHorario) {
        this.strHorario = strHorario;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void onDateSelect(SelectEvent event) {
        Date data = (Date) event.getObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", formatoEmDia.format(event.getObject())));

//        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(statusBotao.getDate1()), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);
        System.out.println("data: " + getDate());
//        parametrosBotoes = getParametrosBotoes(getHorasAtivasPorDia(date), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);

    }

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
    public boolean save() {

        if (!EstudanteMB.isAutentico(this.reserva.getEstudante().getRa(), this.reserva.getEstudante().getSenha())) {
            FacesMessage msg = new FacesMessage("Credenciais inválidas", "Welcome :" + getReserva().getEstudante().getRa());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return false;
        }

        if (reserva.getDataFinal().equals(reserva.getDataInicial())) {
            return false;
        }
        if (reserva.getDataFinal().before(reserva.getDataInicial())) {
            return false;
        }

        EstudanteDAO estudanteDAO = new EstudanteDAO();
        SalaDAO salaDAO = new SalaDAO();

        Date dataInicial = CalendarioHelper.parseDateTime(this.strDataInicial, this.strHorario);
        this.reserva.setDataInicial(dataInicial);

        Estudante estudante = estudanteDAO.obter(this.reserva.getEstudante().getRa());
        this.reserva.setEstudante(estudante);

        Sala sala = salaDAO.obter(this.reserva.getSala().getId());
        this.reserva.setSala(sala);

        if (!reservaDAO.insert(reserva)) {
            FacesMessage msg = new FacesMessage("Fail", "Welcome :" + getReserva().getEstudante().getRa());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return false;
        }

        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + getReserva().getEstudante().getRa());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return true;
    }

    public void alterarEstilo() {
        String parametroUmAtivo = "";
        if (parametroUmAtivo.equals("btn btn-success")) {
            parametroUmAtivo = "ui-priority-primary";
        } else {
            parametroUmAtivo = "btn btn-success";
        }
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

    /**
     * verifica as salas disponiveis
     *
     * @return List<Integer> listaSalasDisponiveis
     */
    public List<Integer> getSalasDisponiveis() {
        List<Integer> listaSalasDisponiveis = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            if (!(idSalasOcupadas.contains(i))) {
                listaSalasDisponiveis.add(i);
            }

        }
        return listaSalasDisponiveis;
    }

    /**
     *
     * @param date
     * @return List<String> listaReservasAtivasPorDia
     */
    public List<String> getHorasAtivasPorDia(Date date) {
        List<Reserva> listaTodasReservas = reservaDAO.list();
        List<String> listaReservasAtivasPorDia = new ArrayList<>();
        String diaProcurado = formatoEmDia.format(date);
        String diaAtivo;
        idSalasOcupadas = new ArrayList<>();

        for (Reserva reserva : listaTodasReservas) {
            diaAtivo = formatoEmDia.format(reserva.getDataInicial());
            if (diaProcurado.equals(diaAtivo)) {
                if (reserva.getStatus().equals(new Status("ativa"))) {
                    listaReservasAtivasPorDia.add(formatoEmHoras.format(reserva.getDataInicial()).substring(0, 2));
                }
                idSalasOcupadas.add(reserva.getId());
            }
        }
        return listaReservasAtivasPorDia;
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
     *
     * @param date uma data válida
     * @return um hashmap que associa data ao conjunto de salas, cada qual com
     * sua reserva
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

    /**
     * Método que faz uma consulta no BD com uma data e um id da strSala,
     * retorna uma lista de reservas correspondente.
     *
     * @param data Date
     * @param sala
     * @param idSala int
     * @return List<Reserva> reservas
     */
    public static List<Reserva> getReservas(Date data, Sala sala) {
        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> allReservas = reservaDAO.list();
        List<Reserva> reservas = new ArrayList<>();

        if (allReservas != null) {
            for (Reserva r : allReservas) {
                if (r.getDataInicial().equals(data) && r.getSala().equals(sala)) {;
                    reservas.add(r);
                }
            }
        }

        if (reservas.isEmpty()) {
            return allReservas;
        }

        return reservas;
    }

    public boolean cancelarReserva(Reserva reserva) {
        throw new UnsupportedOperationException();
    }

    public boolean ativarReserva(Reserva reserva) {
        throw new UnsupportedOperationException();
    }

    public List<Reserva> listarReservas(Estudante estudante) {
        throw new UnsupportedOperationException();
    }

}
