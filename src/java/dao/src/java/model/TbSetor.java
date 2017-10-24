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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbSetor generated by hbm2java
 */
@Entity
@Table(name="tb_setor"
    ,catalog="ata_reuniao"
)
public class TbSetor  implements java.io.Serializable {


     private Integer idtSetor;
     private String nmeSetor;
     private boolean flgAtivo;
     private Set<TaFuncionario> taFuncionarios = new HashSet<TaFuncionario>(0);

    public TbSetor() {
    }

	
    public TbSetor(String nmeSetor, boolean flgAtivo) {
        this.nmeSetor = nmeSetor;
        this.flgAtivo = flgAtivo;
    }
    public TbSetor(String nmeSetor, boolean flgAtivo, Set<TaFuncionario> taFuncionarios) {
       this.nmeSetor = nmeSetor;
       this.flgAtivo = flgAtivo;
       this.taFuncionarios = taFuncionarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idt_setor", unique=true, nullable=false)
    public Integer getIdtSetor() {
        return this.idtSetor;
    }
    
    public void setIdtSetor(Integer idtSetor) {
        this.idtSetor = idtSetor;
    }

    
    @Column(name="nme_setor", nullable=false, length=45)
    public String getNmeSetor() {
        return this.nmeSetor;
    }
    
    public void setNmeSetor(String nmeSetor) {
        this.nmeSetor = nmeSetor;
    }

    
    @Column(name="flg_ativo", nullable=false)
    public boolean isFlgAtivo() {
        return this.flgAtivo;
    }
    
    public void setFlgAtivo(boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tbSetor")
    public Set<TaFuncionario> getTaFuncionarios() {
        return this.taFuncionarios;
    }
    
    public void setTaFuncionarios(Set<TaFuncionario> taFuncionarios) {
        this.taFuncionarios = taFuncionarios;
    }




}


