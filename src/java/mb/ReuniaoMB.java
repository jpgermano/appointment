/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;
import dao.*;
import model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hiragi
 */
@ManagedBean
@ViewScoped
public class ReuniaoMB {

    private TbReuniao selecionado;
    private TbReuniao novo;
    private List<TbReuniao> tbReunioes;
    private List<TbAssunto> tbAssuntos;
    private List<TbProjeto> tbProjetos;
    private List<TbPauta> tbPautas;
    private Integer idtAssuntoPar; 
    private Integer idtProjetoPar; 
    private Integer idtPautaPar; 
    private List<TaParticipantes> taParticipantes;
    private Date dtaReuniao;

    private String nmeLocalReuniao;
    
    /**
     * Creates a new instance of ReuniaoMB
     */
    public ReuniaoMB() {
        selecionado = new TbReuniao();
        nmeLocalReuniao="";
        idtAssuntoPar = 0; 
        idtProjetoPar = 0;
        idtPautaPar = 0;
        
//        TbAssuntoDAO dao = new TbAssuntoDAO();
//        tbAssuntos = dao.consultarTodos();
//        TbProjetoDAO tbProjetoDAO = new TbProjetoDAO();
//        tbProjetoDAO.consultarTodos();
//        TbPautaDAO tbPautaDAO = new TbPautaDAO();
//        tbPautaDAO.consultarTodos();
    }

    public void filtrar() {
        TbReuniaoDAO dao = new TbReuniaoDAO();
        setTbReunioes(dao.consultarPorNmeLocal(getnmeLocalReuniao()));
    }
    
    public void pesquisarPorData() {
        TbReuniaoDAO dao = new TbReuniaoDAO();
        setTbReunioes(dao.consultarPorData(getDtaReuniao()));
    }

    public void novo() {
        setSelecionado(new TbReuniao());
        getSelecionado().setIdtReuniao(0);
        nmeLocalReuniao="";
    }

    public void salvar() {
        TbReuniaoDAO dao = new TbReuniaoDAO();
        if (getSelecionado().getIdtReuniao() == 0) {
            getSelecionado().setIdtReuniao(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbReuniaoDAO dao = new TbReuniaoDAO();
        if (getSelecionado().getIdtReuniao() != 0) {
            if (dao.excluir(getSelecionado().getIdtReuniao())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Exclusão", "Exclusão efetuada com sucesso.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resultado da Exclusao", "Não foi possível excluir.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        filtrar();
    }

    /**
     * @return the selecionado
     */
    public TbReuniao getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbReuniao selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmeLocalReuniao
     */
    public String getnmeLocalReuniao() {
        return nmeLocalReuniao;
    }

    /**
     * @param nmeLocalReuniao the nmeLocalReuniao to set
     */
    public void setnmeLocalReuniao(String nmeLocalReuniao) {
        this.nmeLocalReuniao = nmeLocalReuniao;
    }

    /**
     * @return the tbReunioes
     */
    public List<TbReuniao> getTbReunioes() {
        return tbReunioes;
    }

    /**
     * @param tbReunioes the tbReunioess to set
     */
    public void setTbReunioes(List<TbReuniao> tbReunioes) {
        this.tbReunioes = tbReunioes;
    }

    /**
     * @return the tbAssuntos
     */
    public List<TbAssunto> getTbAssuntos() {
        return tbAssuntos;
    }

    /**
     * @param tbAssuntos the tbAssuntos to set
     */
    public void setTbAssuntos(List<TbAssunto> tbAssuntos) {
        this.tbAssuntos = tbAssuntos;
    }

    /**
     * @return the tbProjetos
     */
    public List<TbProjeto> getTbProjetos() {
        return tbProjetos;
    }

    /**
     * @param tbProjetos the tbProjetos to set
     */
    public void setTbProjetos(List<TbProjeto> tbProjetos) {
        this.tbProjetos = tbProjetos;
    }

    /**
     * @return the tbPautas
     */
    public List<TbPauta> getTbPautas() {
        return tbPautas;
    }

    /**
     * @param tbPautas the tbPautas to set
     */
    public void setTbPautas(List<TbPauta> tbPautas) {
        this.tbPautas = tbPautas;
    }

    /**
     * @return the idtAssuntoPar
     */
    public Integer getIdtAssuntoPar() {
        return idtAssuntoPar;
    }

    /**
     * @param idtAssuntoPar the idtAssuntoPar to set
     */
    public void setIdtAssuntoPar(Integer idtAssuntoPar) {
        this.idtAssuntoPar = idtAssuntoPar;
    }

    /**
     * @return the idtProjetoPar
     */
    public Integer getIdtProjetoPar() {
        return idtProjetoPar;
    }

    /**
     * @param idtProjetoPar the idtProjetoPar to set
     */
    public void setIdtProjetoPar(Integer idtProjetoPar) {
        this.idtProjetoPar = idtProjetoPar;
    }

    /**
     * @return the idtPautaPar
     */
    public Integer getIdtPautaPar() {
        return idtPautaPar;
    }

    /**
     * @param idtPautaPar the idtPautaPar to set
     */
    public void setIdtPautaPar(Integer idtPautaPar) {
        this.idtPautaPar = idtPautaPar;
    }

    /**
     * @return the taParticipantes
     */
    public List<TaParticipantes> getTaParticipantes() {
        return taParticipantes;
    }

    /**
     * @param taParticipantes the taParticipantes to set
     */
    public void setTaParticipantes(List<TaParticipantes> taParticipantes) {
        this.taParticipantes = taParticipantes;
    }
    
    public TbReuniao getNovo() {
        return novo;
    }

    public void setNovo(TbReuniao novo) {
        this.novo = novo;
    }

    public Date getDtaReuniao() {
        return dtaReuniao;
    }

    public void setDtaReuniao(Date dtaReuniao) {
        this.dtaReuniao = dtaReuniao;
    }

    public String getNmeLocalReuniao() {
        return nmeLocalReuniao;
    }

    public void setNmeLocalReuniao(String nmeLocalReuniao) {
        this.nmeLocalReuniao = nmeLocalReuniao;
    }
    
    

}
