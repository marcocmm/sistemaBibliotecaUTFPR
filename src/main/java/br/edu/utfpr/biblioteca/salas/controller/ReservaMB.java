package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.SalaBO;
import tools.CalendarioHelper;
import br.edu.utfpr.biblioteca.salas.model.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    private String dataInicial;
    private String hora;
    private String qtdeAlunos;
    private List<Integer> idSalasOcupadas;
    private List<Integer> horariosReserva;

    //Formatadores de data
    private final SimpleDateFormat formatoEmHoras;
    private final SimpleDateFormat formatoEmDia;

    public ReservaMB() {


        formatoEmHoras = new SimpleDateFormat("HH");
        formatoEmDia = new SimpleDateFormat("dd/MM/yyyy");
        statusBotao = new StatusBotao();
//        Aqui ficou com bug pq o método updateBotoesAtivosPorDia não retorna mais uma lista
//        Ver se é necessário retornar uma lista ou implematar o set do css dos botões no próprio método updateBotoesAtivosPorDia  
//        statusBotao.setParametrosBotoes(updateBotoesAtivosPorDia(dataInicial), date);
        
        horariosReserva = new ArrayList<>();
    }

    public StatusBotao getStatusBotao() {
        return statusBotao;
    }

    public void setStatusBotao(StatusBotao statusBotao) {
        this.statusBotao = statusBotao;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getQtdeAlunos() {
        return qtdeAlunos;
    }

    public void setQtdeAlunos(String qtdeAlunos) {
        this.qtdeAlunos = qtdeAlunos;
    }

    public void onDateSelect(SelectEvent event) {
        Date data = (Date) event.getObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", formatoEmDia.format(event.getObject())));

//        parametrosBotoes = getParametrosBotoes(updateBotoesAtivosPorDia(statusBotao.getDate1()), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);
//        parametrosBotoes = getParametrosBotoes(updateBotoesAtivosPorDia(date), parametroUmAtivo, parametroUmDesativado, parametroDoisAtivo, parametroDoisDesativado);

    }
    
    //O que isto faz?? @author comentar pls
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
        
        //Alterar este método para chamar SalaBO.reservarSala passando os parâmentros corretos já realizado as verificações necessárias.

//        if (!EstudanteMB.isAutentico(this.reserva.getEstudante().getRa(), this.reserva.getEstudante().getSenha())) {
//            FacesMessage msg = new FacesMessage("Credenciais inválidas", "Welcome :" + getReserva().getEstudante().getRa());
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            return false;
//        }
//
//        if (reserva.getDataFinal().equals(reserva.getDataInicial())) {
//            return false;
//        }
//        if (reserva.getDataFinal().before(reserva.getDataInicial())) {
//            return false;
//        }
//
//        EstudanteDAO estudanteDAO = new EstudanteDAO();
//        SalaDAO salaDAO = new SalaDAO();
//
//        Date dataInicial = CalendarioHelper.parseDateTime(this.dataInicial, this.hora);
//        this.reserva.setDataInicial(dataInicial);
//
//        EstudantePO estudante = estudanteDAO.obter(this.reserva.getEstudante().getRa());
//        this.reserva.setEstudante(estudante);
//
//        SalaPO sala = salaDAO.obter(this.reserva.getSala().getId());
//        this.reserva.setSala(sala);
//
//        if (!reservaDAO.insert(reserva)) {
//            FacesMessage msg = new FacesMessage("Fail", "Welcome :" + getReserva().getEstudante().getRa());
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            return false;
//        }
//
//        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + getReserva().getEstudante().getRa());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        return true;
    }


    /**
     * Este método solicita para a classe SalaBO uma lista de salas
     * disponíveis dado um dia e uma hora.
     * Exibe em um Select<html> as salas
     * @return List<Integer> listaSalasDisponiveis
     */
    public List<Integer> viewSalasDisponiveis() {
        List<Integer> listaSalasDisponiveis = new ArrayList<>();
        //implement the code here!
        return listaSalasDisponiveis;
    }

    /**
     * Este método deve solicitar para a classe SalaBO um hash contendo 
     * as salas que possuem horários disponíveis dado um dia. 
     * Ele é quem deve setar o css dos botões.
     */
    public void updateBotoesAtivosPorDia() {
        
        //hash com key inteiro (hora -> 8, 9, 10...) e boolean se existe alguma sala disponível ou todas estão reservas.
        HashMap<Integer, Boolean> salasDisponiveis = SalaBO.getStatusDaSala(this.dataInicial);
        
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
    //O que isto faz??? @author comenatar pls
    private static HashMap<SalaPO, ReservaPO> clone(HashMap<SalaPO, ReservaPO> map) {
        HashMap<SalaPO, ReservaPO> copy = new HashMap();

        for (Entry<SalaPO, ReservaPO> entry : map.entrySet()) {
            try {
                copy.put((SalaPO) entry.getKey().clone(), (ReservaPO) entry.getValue().clone());
            } catch (NullPointerException ex) {
                copy.put((SalaPO) entry.getKey().clone(), null);
            }
        }
        return copy;
    }

    /**
     * Método descreve um dia
     * @param date uma data válida
     * @return um hashmap que associa data ao conjunto de salas, cada qual com
     * sua reserva
     */
    public static HashMap<Date, HashMap<SalaPO, ReservaPO>> descreverDia(Date date) {
        ReservaDAO dao = new ReservaDAO();
        SalaDAO salaDAO = new SalaDAO();

        List<SalaPO> salas = salaDAO.list();
        List<Date> horarios = CalendarioHelper.getHorarios(date);
        List<ReservaPO> reservas = dao.listByDate(date);

        HashMap<SalaPO, ReservaPO> salaTemReservas = new HashMap();
        HashMap<Date, HashMap<SalaPO, ReservaPO>> dataTemReservas = new HashMap();

        for (SalaPO sala : salas) {
            salaTemReservas.put(sala, null);
        }

        for (Date horario : horarios) {
            dataTemReservas.put(horario, clone(salaTemReservas));
        }

        for (ReservaPO reserva : reservas) {
            dataTemReservas.get(reserva.getDataInicial()).put(reserva.getSala(), reserva);
        }
        return dataTemReservas;
    }

    public boolean cancelarReserva(ReservaPO reserva) {
        throw new UnsupportedOperationException();
    }

    public boolean ativarReserva(ReservaPO reserva) {
        throw new UnsupportedOperationException();
    }

    public List<ReservaPO> listarReservas(EstudantePO estudante) {
        throw new UnsupportedOperationException();
    }

}
