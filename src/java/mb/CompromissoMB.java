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
public class CompromissoMB {
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    private TbCompromisso selecionado;
    private List<TbCompromisso> tbCompromissos;
    private String dscCompromisso;
    private Integer idtAssunto; // Usado na combo de novo e edição
    private Integer idtAssuntoPar; // Usado na combo de parâmetros de consulta
    private List<TbAssunto> tbAssuntos;
    private List<TaParticipantes> taParticipantess;
    private Integer idtParticipantes; // Usado na combo de novo e edição

    /**
     * Creates a new instance of CompromissoMB
     */
    public CompromissoMB() {
        selecionado = new TbCompromisso();
        dscCompromisso = "";
        idtAssuntoPar = 0;
        TbAssuntoDAO tbAssuntoDAO = new TbAssuntoDAO();
        tbAssuntos = tbAssuntoDAO.consultarTodos();
        TaParticipantesDAO taParticipantesDAO = new TaParticipantesDAO();
        taParticipantess = taParticipantesDAO.consultarTodos();
        filtrar();
    }

    public void filtrar() {
        TbCompromissoDAO dao = new TbCompromissoDAO();
        setTbCompromissos(dao.consultarCompromissoPorAssunto(getIdtAssuntoPar(), getDscCompromisso()));
    }

    public void novo() {
        setSelecionado(new TbCompromisso());
        getSelecionado().setIdtCompromisso(0);
        setIdtAssunto((Integer) 0);
        setIdtParticipantes((Integer) 0);       
    }

    public void salvar() {       
        TbAssuntoDAO tbAssuntoDAO = new TbAssuntoDAO();
        getSelecionado().setTbAssunto(tbAssuntoDAO.consultarPorIdt(getIdtAssunto()));
        TaParticipantesDAO taParticipantesDAO = new TaParticipantesDAO();
        getSelecionado().setTaParticipantes(taParticipantesDAO.consultarPorIdt(getIdtParticipantes()));
        TbCompromissoDAO dao = new TbCompromissoDAO();
        if (getSelecionado().getIdtCompromisso() == 0) {
            getSelecionado().setIdtCompromisso(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbCompromissoDAO dao = new TbCompromissoDAO();
        if (getSelecionado().getIdtCompromisso() != 0) {
            if (dao.excluir(getSelecionado().getIdtCompromisso())) {
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
    public TbCompromisso getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbCompromisso selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the tbCompromissos
     */
    public List<TbCompromisso> getTbCompromissos() {
        return tbCompromissos;
    }

    /**
     * @param tbCompromissos the tbCompromissos to set
     */
    public void setTbCompromissos(List<TbCompromisso> tbCompromissos) {
        this.tbCompromissos = tbCompromissos;
    }

    /**
     * @return the dscCompromisso
     */
    public String getDscCompromisso() {
        return dscCompromisso;
    }

    /**
     * @param dscCompromisso the dscCompromisso to set
     */
    public void setDscCompromisso(String dscCompromisso) {
        this.dscCompromisso = dscCompromisso;
    }

    /**
     * @return the idtAssunto
     */
    public Integer getIdtAssunto() {
        return idtAssunto;
    }

    /**
     * @param idtAssunto the idtAssunto to set
     */
    public void setIdtAssunto(Integer idtAssunto) {
        this.idtAssunto = idtAssunto;
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
     * @return the taParticipantess
     */
    public List<TaParticipantes> getTaParticipantess() {
        return taParticipantess;
    }

    /**
     * @param taParticipantess the taParticipantess to set
     */
    public void setTaParticipantess(List<TaParticipantes> taParticipantess) {
        this.taParticipantess = taParticipantess;
    }

    /**
     * @return the idtParticipantes
     */
    public Integer getIdtParticipantes() {
        return idtParticipantes;
    }

    /**
     * @param idtParticipantes the idtParticipantes to set
     */
    public void setIdtParticipantes(Integer idtParticipantes) {
        this.idtParticipantes = idtParticipantes;
    }

    


    
}


