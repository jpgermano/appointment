/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.*;
import model.*;

import java.util.List;
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
    private List<TbReuniao> tbReunioes;
    private Integer idtReuniao;
    private List<TbProjeto> tbProjetos;
    private Integer idtProjeto;
    private Integer idtProjetoPar;
    private Date dtaReuniao;
    private String nmeLocalReuniao; 


    /**
     * Creates a new instance of ReuniaoMB
     */
    public ReuniaoMB() {
        selecionado = new TbReuniao();
        nmeLocalReuniao = "";
        idtProjetoPar = 0;

        TbProjetoDAO tbProjetoDAO = new TbProjetoDAO();
        tbProjetos = tbProjetoDAO.consultarTodos();
        filtrar();
        
    }

    public void filtrar() {
        TbReuniaoDAO dao = new TbReuniaoDAO();
        setTbReunioes(dao.consultarPorNmeLocal(nmeLocalReuniao));
    }

    public void pesquisarPorData() {
        TbReuniaoDAO dao = new TbReuniaoDAO();
        setTbReunioes(dao.consultarPorData(dtaReuniao));
    }

    public void novo() {
        setSelecionado(new TbReuniao());
        getSelecionado().setIdtReuniao(0);
        idtProjeto = 0;
    }

    public void salvar() {
        TbProjetoDAO tbProjetoDAO = new TbProjetoDAO();
        selecionado.setTbProjeto(tbProjetoDAO.consultarPorIdt(idtProjeto));
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
     * @return the idtReuniao
     */
    public Integer getIdtReuniao() {
        return idtReuniao;
    }

    /**
     * @param idtReuniao the idtReuniao to set
     */
    public void setIdtReuniao(Integer idtReuniao) {
        this.idtReuniao = idtReuniao;
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
     * @return the idtProjeto
     */
    public Integer getIdtProjeto() {
        return idtProjeto;
    }

    /**
     * @param idtProjeto the idtProjeto to set
     */
    public void setIdtProjeto(Integer idtProjeto) {
        this.idtProjeto = idtProjeto;
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
     * @return the dtaReuniao
     */
    public Date getDtaReuniao() {
        return dtaReuniao;
    }

    /**
     * @param dtaReuniao the dtaReuniao to set
     */
    public void setDtaReuniao(Date dtaReuniao) {
        this.dtaReuniao = dtaReuniao;
    }

    /**
     * @return the nmeLocalReuniao
     */
    public String getNmeLocalReuniao() {
        return nmeLocalReuniao;
    }

    /**
     * @param nmeLocalReuniao the nmeLocalReuniao to set
     */
    public void setNmeLocalReuniao(String nmeLocalReuniao) {
        this.nmeLocalReuniao = nmeLocalReuniao;
    }

    /**
     * @return the tbReunioes
     */
    public List<TbReuniao> getTbReunioes() {
        return tbReunioes;
    }

    /**
     * @param tbReunioes the tbReunioes to set
     */
    public void setTbReunioes(List<TbReuniao> tbReunioes) {
        this.tbReunioes = tbReunioes;
    }


    
    

 

}
