package br.edu.utfpr.biblioteca.salas.model.entity;

import br.edu.utfpr.biblioteca.salas.model.dao.StatusDAO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name = "Reserva")
@Table(name = "Reservas")
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")})
public class ReservaPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_alunos")
    private int quantidadeAlunos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;
    private transient String strDataInicial;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;
    private transient String strDataFinal;

    @JoinColumn(name = "estudante_ra", referencedColumnName = "ra")
    @ManyToOne(optional = false)
    private EstudantePO estudante;

    @JoinColumn(name = "sala_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SalaPO sala;

    @JoinColumn(name = "status_name", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private StatusPO status;

    protected ReservaPO() {
    }

    public ReservaPO(EstudantePO estudante, SalaPO sala, Date dataInicial, int quantidadeAlunos) {
        this.estudante = estudante;
        this.sala = sala;
        this.dataInicial = dataInicial;
        if (dataInicial != null) {
            this.dataFinal = CalendarioHelper.addHora(this.dataInicial);
        } else {
            this.dataFinal = new Date();
        }
        this.quantidadeAlunos = quantidadeAlunos;
        StatusDAO statusDAO = new StatusDAO();
        StatusPO inativa = statusDAO.obter("inativa");
        if (inativa == null) {
            inativa = new StatusPO("inativa");
        }
        this.status = inativa;
    }

    public Integer getId() {
        return id;
    }
    public String getStrId(){
        return id.toString();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidadeAlunos() {
        if (quantidadeAlunos > 5) {
            quantidadeAlunos = 5;
        }
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
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

    public EstudantePO getEstudante() {
        return estudante;
    }

    public void setEstudante(EstudantePO estudante) {
        this.estudante = estudante;
    }

    public SalaPO getSala() {
        return sala;
    }

    public void setSala(SalaPO sala) {
        this.sala = sala;
    }

    public StatusPO getStatus() {
        return status;
    }

    public void setStatus(StatusPO status) {
        this.status = status;
    }

    public void setStrDataFinal(String strDataFinal) {
        this.strDataFinal = strDataFinal;
    }

    public void setStrDataInicial(String strDataInicial) {
        this.strDataInicial = strDataInicial;
    }

    public String getStrDataFinal() {
        return strDataFinal;
    }

    public String getStrDataInicial() {
        return strDataInicial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaPO)) {
            return false;
        }
        ReservaPO other = (ReservaPO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.biblioteca.salas.model.Reserva[ id=" + id + " ]";
    }

    @Override
    public Object clone() {
        ReservaPO reserva = new ReservaPO();
        reserva.setId(this.id);
        reserva.setEstudante(this.estudante);
        reserva.setSala(this.sala);
        reserva.setDataInicial(this.dataInicial);
        reserva.setDataFinal(this.dataFinal);
        reserva.setQuantidadeAlunos(this.quantidadeAlunos);
        reserva.setStatus(this.status);
        return reserva;
    }

}
