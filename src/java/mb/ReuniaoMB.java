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
    private TbReuniao novo;
    private List<TbReuniao> tbReunioes;
    private Integer idtReuniao;
    private List<TbAssunto> tbAssuntos;
    private Integer idtAssuntoPar;
    private Integer idtAssunto;
    private List<TbCompromisso> tbCompromissos;
    private Integer idtCompromisso;
    private List<TbProjeto> tbProjetos;
    private Integer idtProjeto;
    private Integer idtProjetoPar;
    private List<TbPauta> tbPautas;
    private Integer idtPauta;
    private Integer idtPautaPar;
    private List<TaParticipantes> taParticipantes;
    private Integer idtParticipantes;
    
    private Date dtaReuniao;
    private String nmeLocalReuniao; 
    
    private TbAssunto assunto;
    private TbPauta pauta;
    private TaParticipantes participantes;
    private TbCompromisso compromisso;

    /**
     * Creates a new instance of ReuniaoMB
     */
    public ReuniaoMB() {
        selecionado = new TbReuniao();
        nmeLocalReuniao = "";
        idtAssuntoPar = 0;
        idtProjetoPar = 0;
        idtPautaPar = 0;

        TbProjetoDAO tbProjetoDAO = new TbProjetoDAO();
        tbProjetos = tbProjetoDAO.consultarTodos();
        TbAssuntoDAO tbAssuntoDAO = new TbAssuntoDAO();
        tbAssuntos = tbAssuntoDAO.consultarTodos();
        TbCompromissoDAO tbCompromissoDAO = new TbCompromissoDAO();
        tbCompromissos = tbCompromissoDAO.consultarTodos();
        TaParticipantesDAO taParticipantesDAO = new TaParticipantesDAO();
        taParticipantes = taParticipantesDAO.consultarTodos();
        TbPautaDAO tbPautaDAO = new TbPautaDAO();
        tbPautas = tbPautaDAO.consultarTodos();
        idtReuniao = 0;
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
        idtCompromisso = 0;
        idtPauta = 0;
        idtAssunto = 0;
        nmeLocalReuniao = "";
    }

    public void salvar() {
        TbProjetoDAO tbProjetoDAO = new TbProjetoDAO();
        selecionado.setTbProjeto(tbProjetoDAO.consultarPorIdt(idtProjeto));

        TbAssuntoDAO tbAssuntoDAO = new TbAssuntoDAO();
        assunto = tbAssuntoDAO.consultarPorIdt(idtAssunto);
        TbCompromissoDAO tbCompromissoDAO = new TbCompromissoDAO();
        compromisso = tbCompromissoDAO.consultarPorIdt(idtCompromisso);
        TaParticipantesDAO taParticipantesDAO = new TaParticipantesDAO();
        participantes = taParticipantesDAO.consultarPorIdt(idtParticipantes);
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
     * @return the novo
     */
    public TbReuniao getNovo() {
        return novo;
    }

    /**
     * @param novo the novo to set
     */
    public void setNovo(TbReuniao novo) {
        this.novo = novo;
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
     * @return the idtCompromisso
     */
    public Integer getIdtCompromisso() {
        return idtCompromisso;
    }

    /**
     * @param idtCompromisso the idtCompromisso to set
     */
    public void setIdtCompromisso(Integer idtCompromisso) {
        this.idtCompromisso = idtCompromisso;
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
     * @return the assunto
     */
    public TbAssunto getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(TbAssunto assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the pauta
     */
    public TbPauta getPauta() {
        return pauta;
    }

    /**
     * @param pauta the pauta to set
     */
    public void setPauta(TbPauta pauta) {
        this.pauta = pauta;
    }

    /**
     * @return the participantes
     */
    public TaParticipantes getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(TaParticipantes participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the compromisso
     */
    public TbCompromisso getCompromisso() {
        return compromisso;
    }

    /**
     * @param compromisso the compromisso to set
     */
    public void setCompromisso(TbCompromisso compromisso) {
        this.compromisso = compromisso;
    }
    
    

 

}
