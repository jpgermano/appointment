/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;


import dao.*;
import model.*;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author RA21485532
 */
@ManagedBean
@ViewScoped
public class AssuntoMB {
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    private TbAssunto selecionado;
    private List<TbAssunto> tbAssuntos;
    private String txtAssunto;
    private Integer idtReuniaoPar; // Usado na combo de parâmetros de consulta
    private Integer idtPautaPar; // Usado na combo de parâmetros de consulta
    private List<TbReuniao> tbReuniaos;
    private List<TbPauta> tbPautas;
    private Integer idtReuniao; // Usado na combo de novo e edição
    private Integer idtPauta; // Usado na combo de novo e edição

    /**
     * Creates a new instance of AssuntoMB
     */
    public AssuntoMB() {
        selecionado = new TbAssunto();
        txtAssunto = "";
        idtReuniaoPar = 0;
        idtPautaPar = 0;
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReuniaos = dao.consultarTodos();
        TbPautaDAO tbPautaDAO = new TbPautaDAO();
        tbPautas = tbPautaDAO.consultarTodos();
        filtrar();
    }

    public void filtrar() {
        TbAssuntoDAO dao = new TbAssuntoDAO();
        setTbAssuntos(dao.consultarPorReuniaoTxt(getIdtReuniaoPar(), txtAssunto));
    }

    public void novo() {
        setSelecionado(new TbAssunto());
        getSelecionado().setIdtAssunto(0);    
    }

    public void salvar() {
        TbAssuntoDAO dao = new TbAssuntoDAO();
        if (getSelecionado().getIdtAssunto() == 0) {
            getSelecionado().setIdtAssunto(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbAssuntoDAO dao = new TbAssuntoDAO();
        if (getSelecionado().getIdtAssunto() != 0) {
            if (dao.excluir(getSelecionado().getIdtAssunto())) {
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
    public TbAssunto getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbAssunto selecionado) {
        this.selecionado = selecionado;
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
     * @return the txtAssunto
     */
    public String getTxtAssunto() {
        return txtAssunto;
    }

    /**
     * @param txtAssunto the txtAssunto to set
     */
    public void setTxtAssunto(String txtAssunto) {
        this.txtAssunto = txtAssunto;
    }

    /**
     * @return the idtReuniaoPar
     */
    public Integer getIdtReuniaoPar() {
        return idtReuniaoPar;
    }

    /**
     * @param idtReuniaoPar the idtReuniaoPar to set
     */
    public void setIdtReuniaoPar(Integer idtReuniaoPar) {
        this.idtReuniaoPar = idtReuniaoPar;
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
     * @return the tbReuniaos
     */
    public List<TbReuniao> getTbReuniaos() {
        return tbReuniaos;
    }

    /**
     * @param tbReuniaos the tbReuniaos to set
     */
    public void setTbReuniaos(List<TbReuniao> tbReuniaos) {
        this.tbReuniaos = tbReuniaos;
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
     * @return the idtPauta
     */
    public Integer getIdtPauta() {
        return idtPauta;
    }

    /**
     * @param idtPauta the idtPauta to set
     */
    public void setIdtPauta(Integer idtPauta) {
        this.idtPauta = idtPauta;
    }


    
}


