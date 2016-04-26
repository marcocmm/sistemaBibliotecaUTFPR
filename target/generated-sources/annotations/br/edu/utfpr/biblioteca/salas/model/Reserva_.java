package br.edu.utfpr.biblioteca.salas.model;

import br.edu.utfpr.biblioteca.salas.model.Estudante;
import br.edu.utfpr.biblioteca.salas.model.ReservaAtiva;
import br.edu.utfpr.biblioteca.salas.model.Sala;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-22T23:57:46")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile ListAttribute<Reserva, ReservaAtiva> reservasAtivasList;
    public static volatile SingularAttribute<Reserva, Integer> quantidadeAlunos;
    public static volatile SingularAttribute<Reserva, Sala> salaId;
    public static volatile SingularAttribute<Reserva, Integer> id;
    public static volatile SingularAttribute<Reserva, Date> dataInicial;
    public static volatile ListAttribute<Reserva, ReservaAtiva> reservasAtivasList2;
    public static volatile ListAttribute<Reserva, ReservaAtiva> reservasAtivasList1;
    public static volatile SingularAttribute<Reserva, Date> dataFinal;
    public static volatile SingularAttribute<Reserva, Estudante> estudanteRa;

}