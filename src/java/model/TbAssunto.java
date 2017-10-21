package model;
// Generated 07/10/2017 10:54:24 by Hibernate Tools 4.3.1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbAssunto generated by hbm2java
 */
@Entity
@Table(name="tb_assunto"
    ,catalog="ata_reuniao"
)
public class TbAssunto  implements java.io.Serializable {


     private Integer idtAssunto;
     private TbPauta tbPauta;
     private TbReuniao tbReuniao;
     private Date hraInicioAssunto;
     private Date hraTerminoAssunto;
     private Date hraDuracaoAssunto;
     private String txtPautaExtraAssunto;
     private String txtAssunto;
     private Set<TbCompromisso> tbCompromissos = new HashSet<TbCompromisso>(0);

    public TbAssunto() {
    }

	
    public TbAssunto(TbReuniao tbReuniao, Date hraInicioAssunto, Date hraTerminoAssunto, Date hraDuracaoAssunto) {
        this.tbReuniao = tbReuniao;
        this.hraInicioAssunto = hraInicioAssunto;
        this.hraTerminoAssunto = hraTerminoAssunto;
        this.hraDuracaoAssunto = hraDuracaoAssunto;
    }
    public TbAssunto(TbPauta tbPauta, TbReuniao tbReuniao, Date hraInicioAssunto, Date hraTerminoAssunto, Date hraDuracaoAssunto, String txtPautaExtraAssunto, String txtAssunto, Set<TbCompromisso> tbCompromissos) {
       this.tbPauta = tbPauta;
       this.tbReuniao = tbReuniao;
       this.hraInicioAssunto = hraInicioAssunto;
       this.hraTerminoAssunto = hraTerminoAssunto;
       this.hraDuracaoAssunto = hraDuracaoAssunto;
       this.txtPautaExtraAssunto = txtPautaExtraAssunto;
       this.txtAssunto = txtAssunto;
       this.tbCompromissos = tbCompromissos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idt_assunto", unique=true, nullable=false)
    public Integer getIdtAssunto() {
        return this.idtAssunto;
    }
    
    public void setIdtAssunto(Integer idtAssunto) {
        this.idtAssunto = idtAssunto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_pauta")
    public TbPauta getTbPauta() {
        return this.tbPauta;
    }
    
    public void setTbPauta(TbPauta tbPauta) {
        this.tbPauta = tbPauta;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_reuniao", nullable=false)
    public TbReuniao getTbReuniao() {
        return this.tbReuniao;
    }
    
    public void setTbReuniao(TbReuniao tbReuniao) {
        this.tbReuniao = tbReuniao;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="hra_inicio_assunto", nullable=false, length=19)
    public Date getHraInicioAssunto() {
        return this.hraInicioAssunto;
    }
    
    public void setHraInicioAssunto(Date hraInicioAssunto) {
        this.hraInicioAssunto = hraInicioAssunto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="hra_termino_assunto", nullable=false, length=19)
    public Date getHraTerminoAssunto() {
        return this.hraTerminoAssunto;
    }
    
    public void setHraTerminoAssunto(Date hraTerminoAssunto) {
        this.hraTerminoAssunto = hraTerminoAssunto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="hra_duracao_assunto", nullable=false, length=19)
    public Date getHraDuracaoAssunto() {
        return this.hraDuracaoAssunto;
    }
    
    public void setHraDuracaoAssunto(Date hraDuracaoAssunto) {
        this.hraDuracaoAssunto = hraDuracaoAssunto;
    }

    
    @Column(name="txt_pauta_extra_assunto", length=65535)
    public String getTxtPautaExtraAssunto() {
        return this.txtPautaExtraAssunto;
    }
    
    public void setTxtPautaExtraAssunto(String txtPautaExtraAssunto) {
        this.txtPautaExtraAssunto = txtPautaExtraAssunto;
    }

    
    @Column(name="txt_assunto", length=65535)
    public String getTxtAssunto() {
        return this.txtAssunto;
    }
    
    public void setTxtAssunto(String txtAssunto) {
        this.txtAssunto = txtAssunto;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tbAssunto")
    public Set<TbCompromisso> getTbCompromissos() {
        return this.tbCompromissos;
    }
    
    public void setTbCompromissos(Set<TbCompromisso> tbCompromissos) {
        this.tbCompromissos = tbCompromissos;
    }




}


