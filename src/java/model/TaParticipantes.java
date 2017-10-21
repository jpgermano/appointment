package model;
// Generated 07/10/2017 10:54:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TaParticipantes generated by hbm2java
 */
@Entity
@Table(name="ta_participantes"
    ,catalog="ata_reuniao"
)
public class TaParticipantes  implements java.io.Serializable {


     private Integer idtParticipantes;
     private TaFuncionario taFuncionario;
     private TbFuncao tbFuncao;
     private TbReuniao tbReuniao;
     private Set<TbCompromisso> tbCompromissos = new HashSet<TbCompromisso>(0);

    public TaParticipantes() {
    }

	
    public TaParticipantes(TaFuncionario taFuncionario, TbFuncao tbFuncao, TbReuniao tbReuniao) {
        this.taFuncionario = taFuncionario;
        this.tbFuncao = tbFuncao;
        this.tbReuniao = tbReuniao;
    }
    public TaParticipantes(TaFuncionario taFuncionario, TbFuncao tbFuncao, TbReuniao tbReuniao, Set<TbCompromisso> tbCompromissos) {
       this.taFuncionario = taFuncionario;
       this.tbFuncao = tbFuncao;
       this.tbReuniao = tbReuniao;
       this.tbCompromissos = tbCompromissos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idt_participantes", unique=true, nullable=false)
    public Integer getIdtParticipantes() {
        return this.idtParticipantes;
    }
    
    public void setIdtParticipantes(Integer idtParticipantes) {
        this.idtParticipantes = idtParticipantes;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_funcionario", nullable=false)
    public TaFuncionario getTaFuncionario() {
        return this.taFuncionario;
    }
    
    public void setTaFuncionario(TaFuncionario taFuncionario) {
        this.taFuncionario = taFuncionario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_funcao", nullable=false)
    public TbFuncao getTbFuncao() {
        return this.tbFuncao;
    }
    
    public void setTbFuncao(TbFuncao tbFuncao) {
        this.tbFuncao = tbFuncao;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_reuniao", nullable=false)
    public TbReuniao getTbReuniao() {
        return this.tbReuniao;
    }
    
    public void setTbReuniao(TbReuniao tbReuniao) {
        this.tbReuniao = tbReuniao;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="taParticipantes")
    public Set<TbCompromisso> getTbCompromissos() {
        return this.tbCompromissos;
    }
    
    public void setTbCompromissos(Set<TbCompromisso> tbCompromissos) {
        this.tbCompromissos = tbCompromissos;
    }




}


