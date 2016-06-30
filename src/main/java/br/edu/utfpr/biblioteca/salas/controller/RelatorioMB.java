package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
import java.util.Date;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@Named(value = "relatorioMB")
@ViewScoped
@ManagedBean
public class RelatorioMB {

    private Date dataInicial;
    private Date dataFinal;

    public RelatorioMB() {
        this.dataInicial = new Date();
        this.dataFinal = new Date();
    }
    
    public boolean isAdministradorLoggedIn(){
        UsuarioPO usuarioLogado = SessionContext.getInstance().getUsuarioLogado();
        if (usuarioLogado == null) {
            return false;
        }
        return usuarioLogado.isAdministrador();
    }
}