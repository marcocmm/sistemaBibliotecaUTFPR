package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.bo.AdministradorBO;
import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
import java.io.InputStream;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named(value = "relatorioMB")
@ViewScoped
@ManagedBean
public class RelatorioMB {

    private Date dataInicial;
    private Date dataFinal;
    private String tipoRelatorio;
    private Boolean readyToDownload = false;
    private String urlDonwload = "";
    private StreamedContent file;

    public RelatorioMB() {
        this.dataInicial = new Date();
        this.dataFinal = new Date();

    }

    public boolean isAdministradorLoggedIn() {
        UsuarioPO usuarioLogado = SessionContext.getInstance().getUsuarioLogado();
        if (usuarioLogado == null) {
            return false;
        }
        return usuarioLogado.isAdministrador();
    }

    public void gerarRelatorio(ActionEvent event) {
        AdministradorBO administradorBO = new AdministradorBO();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        if (tipoRelatorio.isEmpty()){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao gerar relatório", "Você deve selecionar um tipo de relatório"));
        }
        
        try {
            administradorBO.gerarRelatorio(tipoRelatorio, dataInicial, dataFinal);
            fileDownloadView();
            readyToDownload = true;
        } catch (Exception ex) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao gerar relatório", ""));
        }
    }

    public String exibirBotaoDownloadRelatorio() {
        if (readyToDownload) {
            return "false";
        }
        return "true";
    }

    public void fileDownloadView() {
        InputStream stream;
        if (tipoRelatorio.equals("1")) {
            stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/relatorios/RelatorioSemanal.csv");
            this.file = new DefaultStreamedContent(stream, "application/csv", "downloaded_RelatorioSemanal.csv");
        } else if (tipoRelatorio.equals("2")) {
            stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/relatorios/RelatorioMensal.csv");
            this.file = new DefaultStreamedContent(stream, "application/csv", "downloaded_RelatorioMensal.csv");
        } else {
            stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/relatorios/RelatorioAnual.csv");
            this.file = new DefaultStreamedContent(stream, "application/csv", "downloaded_RelatorioAnual.csv");
        }
    }

    public StreamedContent getFile() {
        InputStream stream;
        if (tipoRelatorio.equals("1")) {
            stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/relatorios/RelatorioSemanal.csv");
            return new DefaultStreamedContent(stream, "application/csv", "downloaded_RelatorioSemanal.csv");
        } else if (tipoRelatorio.equals("2")) {
            stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/relatorios/RelatorioMensal.csv");
            return new DefaultStreamedContent(stream, "application/csv", "downloaded_RelatorioMensal.csv");
        } else {
            stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/relatorios/RelatorioAnual.csv");
            return new DefaultStreamedContent(stream, "application/csv", "downloaded_RelatorioAnual.csv");
        }
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public String getUrlDonwload() {
        return urlDonwload;
    }

    public void setUrlDonwload(String urlDonwload) {
        this.urlDonwload = urlDonwload;
    }

}
