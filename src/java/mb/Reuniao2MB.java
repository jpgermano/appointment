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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hiragi
 */
@ManagedBean
@ViewScoped
public class Reuniao2MB {



    private TbReuniao selecionado;
    private List<TbReuniao> tbReunioes;
    private Integer idtReuniao;
    private List<TbProjeto> tbProjetos;
    private Integer idtProjeto;
    private Integer idtProjetoPar;
    private Date dtaReuniao;
    private String nmeLocalReuniao; 
    
    //Participantes
    private TaParticipantes selecionadoParticipantes;
    private List<TaParticipantes> taParticipantess;
    private List<TbFuncao> tbFuncoes;
    private Integer idtFuncao; // Usado na combo de novo e edição
    //private List<TbReuniao> tbReunioes;
    //private Integer idtReuniao; // Usado na combo de novo e edição
    private List<TaFuncionario> taFuncionarios;
    private Integer idtFuncionario; // Usado na combo de novo e edição
    
    //Pauta
    private TbPauta selecionadoPauta;
    private List<TbPauta> tbPautas;
    private String txtPauta;
    //private List<TbReuniao> tbReuniaos;
    //private Integer idtReuniao; // Usado na combo de novo e edição
    
    //Assunto
    private TbAssunto selecionadoAssunto;
    private List<TbAssunto> tbAssuntos;
    private String txtAssunto;
    private Integer idtPautaPar; // Usado na combo de parâmetros de consulta
    //private List<TbReuniao> tbReuniaos;
    //private List<TbPauta> tbPautas;
    //private Integer idtReuniao; // Usado na combo de novo e edição
    private Integer idtPauta; // Usado na combo de novo e edição
    
    //Compromisso
    private TbCompromisso selecionadoCompromisso;
    private List<TbCompromisso> tbCompromissos;
    private String dscCompromisso;
    private Integer idtAssunto; // Usado na combo de novo e edição
    //private List<TbAssunto> tbAssuntos;
    //private List<TaParticipantes> taParticipantess;
    private Integer idtParticipantes; // Usado na combo de novo e edição
    

    /**
     * Creates a new instance of ReuniaoMB
     */
    public Reuniao2MB() {
        selecionado = new TbReuniao();
        nmeLocalReuniao = "";
        idtProjetoPar = 0;

        TbProjetoDAO tbProjetoDAO = new TbProjetoDAO();
        tbProjetos = tbProjetoDAO.consultarTodos();
        
        filtrar();
        participantes();
        
    }
    public void pesquisarTodos(){
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReunioes = dao.consultarTodos();
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
        TbReuniaoDAO dao = new TbReuniaoDAO();
        TbReuniao retorno = new TbReuniao();
        if (getSelecionado().getIdtReuniao() == 0) {
            getSelecionado().setIdtReuniao(null);
            retorno = dao.incluir(getSelecionado());
        } else {
            retorno = dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        HttpSession sessaoReuniao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sessaoReuniao.setAttribute("reuniao", retorno);
        participantesNovo();
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
    
       //Parte 1
    public void participantes(){
        selecionadoParticipantes = new TaParticipantes();
       
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReunioes = dao.consultarTodos();
        
        TbFuncaoDAO tbFuncaoDAO = new TbFuncaoDAO();
        tbFuncoes = tbFuncaoDAO.consultarTodos();
        
        TaFuncionarioDAO taFuncionarioDAO = new TaFuncionarioDAO();
        taFuncionarios = taFuncionarioDAO.consultarTodos();

    }
    
    public void participantesNovo(){
        setSelecionadoParticipantes(new TaParticipantes());
        getSelecionadoParticipantes().setIdtParticipantes(0);

        
    }
    public void participantesSalvar(){
        HttpSession sessaoReuniao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        TbReuniao reuniaoo = (TbReuniao) sessaoReuniao.getAttribute("reuniao");
        TbReuniaoDAO tbReuniaoDAO = new TbReuniaoDAO();
        getSelecionadoParticipantes().setTbReuniao(tbReuniaoDAO.consultarPorIdt(reuniaoo.getIdtReuniao()));
        TaParticipantesDAO dao = new TaParticipantesDAO();
        TaParticipantes retorno = new TaParticipantes();

        if (getSelecionadoParticipantes().getIdtParticipantes()== 0) {
            getSelecionadoParticipantes().setIdtParticipantes(null);
            retorno = dao.incluir(getSelecionadoParticipantes());
        } else {
            retorno = dao.juntar(getSelecionadoParticipantes());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        HttpSession sessaoParticipantes = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sessaoParticipantes.setAttribute("participantes", retorno);
        filtrar();
    }

    
    //Parte 2
    public void pauta(){
        selecionadoPauta = new TbPauta();
        txtPauta = "";
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReunioes = dao.consultarTodos();
    }
    
    public void pautaNova(){
        setSelecionadoPauta(new TbPauta());
        getSelecionadoPauta().setIdtPauta(0);
    }
    public void pautaSalvar(){
        HttpSession sessaoReuniao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        TbReuniao reuniaoo = (TbReuniao) sessaoReuniao.getAttribute("reuniao");
        TbReuniaoDAO tbReuniaoDAO = new TbReuniaoDAO();
        getSelecionadoPauta().setTbReuniao(tbReuniaoDAO.consultarPorIdt(reuniaoo.getIdtReuniao()));
        TbPautaDAO dao = new TbPautaDAO();
        TbPauta retorno = new TbPauta();
        if (getSelecionadoPauta().getIdtPauta() == 0) {
            getSelecionadoPauta().setIdtPauta(null);
            retorno = dao.incluir(getSelecionadoPauta());
        } else {
            retorno = dao.juntar(getSelecionadoPauta());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        HttpSession sessaoPauta = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sessaoPauta.setAttribute("pauta", retorno);
        filtrar();
    }
    
    //Parte 3
    public void assunto(){
        selecionadoAssunto = new TbAssunto();
        txtAssunto = "";
        idtPautaPar = 0;
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReunioes = dao.consultarTodos();
        TbPautaDAO tbPautaDAO = new TbPautaDAO();
        tbPautas = tbPautaDAO.consultarTodos();
    }
    public void assuntoNovo(){
        setSelecionadoAssunto(new TbAssunto());
        getSelecionadoAssunto().setIdtAssunto(0);

    }
    public void assuntoSalvar(){
        HttpSession sessaoReuniao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        TbReuniao reuniaoo = (TbReuniao) sessaoReuniao.getAttribute("reuniao");
        TbReuniaoDAO tbReuniaoDAO = new TbReuniaoDAO();
        getSelecionadoAssunto().setTbReuniao(tbReuniaoDAO.consultarPorIdt(reuniaoo.getIdtReuniao()));
        
        HttpSession sessaoPauta = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        TbPauta pauta = (TbPauta) sessaoPauta.getAttribute("pauta");
        TbPautaDAO tbPautaDAO = new TbPautaDAO();
        getSelecionadoAssunto().setTbPauta(tbPautaDAO.consultarPorIdt(pauta.getIdtPauta()));
        
        TbAssuntoDAO dao = new TbAssuntoDAO();
        TbAssunto assunto = new TbAssunto();
        if (getSelecionadoAssunto().getIdtAssunto() == 0) {
            getSelecionadoAssunto().setIdtAssunto(null);
            assunto = dao.incluir(getSelecionadoAssunto());
        } else {
            assunto = dao.juntar(getSelecionadoAssunto());
        }
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        HttpSession sessaoAssunto = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sessaoPauta.setAttribute("assunto", assunto);
        filtrar();
    }
    //Parte 4
    public void compromisso(){
        selecionadoCompromisso = new TbCompromisso();
        dscCompromisso = "";
        TbAssuntoDAO tbAssuntoDAO = new TbAssuntoDAO();
        tbAssuntos = tbAssuntoDAO.consultarTodos();
        TaParticipantesDAO taParticipantesDAO = new TaParticipantesDAO();
        taParticipantess = taParticipantesDAO.consultarTodos();
    }
    public void compromissoNovo(){
        setSelecionadoCompromisso(new TbCompromisso());
        getSelecionadoCompromisso().setIdtCompromisso(0);
    }
    public void compromissoSalvar(){
        HttpSession sessaoAssunto = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        TbAssunto assunto = (TbAssunto) sessaoAssunto.getAttribute("assunto");
        TbAssuntoDAO tbAssuntoDAO = new TbAssuntoDAO();
        getSelecionadoCompromisso().setTbAssunto(tbAssuntoDAO.consultarPorIdt(assunto.getIdtAssunto()));
        
        HttpSession sessaoParticipantes = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        TaParticipantes participante = (TaParticipantes) sessaoParticipantes.getAttribute("participantes");
        TaParticipantesDAO taParticipantesDAO = new TaParticipantesDAO();
        getSelecionadoCompromisso().setTaParticipantes(taParticipantesDAO.consultarPorIdt(participante.getIdtParticipantes()));
        
        TbCompromissoDAO dao = new TbCompromissoDAO();
        if (getSelecionadoCompromisso().getIdtCompromisso() == 0) {
            getSelecionadoCompromisso().setIdtCompromisso(null);
            dao.incluir(getSelecionadoCompromisso());
        } else {
            dao.juntar(getSelecionadoCompromisso());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void editarParticipantes(){
        TbReuniaoDAO tbReuniaoDAO = new TbReuniaoDAO();
        getSelecionadoParticipantes().setTbReuniao(tbReuniaoDAO.consultarPorIdt(idtReuniao));
        getSelecionadoParticipantes().setTbReuniao(tbReuniaoDAO.consultarPorIdt(idtReuniao));
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

    //novos

    /**
     * @return the selecionadoParticipantes
     */
    public TaParticipantes getSelecionadoParticipantes() {
        return selecionadoParticipantes;
    }

    /**
     * @param selecionadoParticipantes the selecionadoParticipantes to set
     */
    public void setSelecionadoParticipantes(TaParticipantes selecionadoParticipantes) {
        this.selecionadoParticipantes = selecionadoParticipantes;
    }


    /**
     * @return the tbFuncoes
     */
    public List<TbFuncao> getTbFuncoes() {
        return tbFuncoes;
    }

    /**
     * @param tbFuncoes the tbFuncoes to set
     */
    public void setTbFuncoes(List<TbFuncao> tbFuncoes) {
        this.tbFuncoes = tbFuncoes;
    }

    /**
     * @return the idtFuncao
     */
    public Integer getIdtFuncao() {
        return idtFuncao;
    }

    /**
     * @param idtFuncao the idtFuncao to set
     */
    public void setIdtFuncao(Integer idtFuncao) {
        this.idtFuncao = idtFuncao;
    }

    /**
     * @return the taFuncionarios
     */
    public List<TaFuncionario> getTaFuncionarios() {
        return taFuncionarios;
    }

    /**
     * @param taFuncionarios the taFuncionarios to set
     */
    public void setTaFuncionarios(List<TaFuncionario> taFuncionarios) {
        this.taFuncionarios = taFuncionarios;
    }

    /**
     * @return the idtFuncionario
     */
    public Integer getIdtFuncionario() {
        return idtFuncionario;
    }

    /**
     * @param idtFuncionario the idtFuncionario to set
     */
    public void setIdtFuncionario(Integer idtFuncionario) {
        this.idtFuncionario = idtFuncionario;
    }

    /**
     * @return the selecionadoPauta
     */
    public TbPauta getSelecionadoPauta() {
        return selecionadoPauta;
    }

    /**
     * @param selecionadoPauta the selecionadoPauta to set
     */
    public void setSelecionadoPauta(TbPauta selecionadoPauta) {
        this.selecionadoPauta = selecionadoPauta;
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
     * @return the txtPauta
     */
    public String getTxtPauta() {
        return txtPauta;
    }

    /**
     * @param txtPauta the txtPauta to set
     */
    public void setTxtPauta(String txtPauta) {
        this.txtPauta = txtPauta;
    }

    /**
     * @return the selecionadoAssunto
     */
    public TbAssunto getSelecionadoAssunto() {
        return selecionadoAssunto;
    }

    /**
     * @param selecionadoAssunto the selecionadoAssunto to set
     */
    public void setSelecionadoAssunto(TbAssunto selecionadoAssunto) {
        this.selecionadoAssunto = selecionadoAssunto;
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
     * @return the selecionadoCompromisso
     */
    public TbCompromisso getSelecionadoCompromisso() {
        return selecionadoCompromisso;
    }

    /**
     * @param selecionadoCompromisso the selecionadoCompromisso to set
     */
    public void setSelecionadoCompromisso(TbCompromisso selecionadoCompromisso) {
        this.selecionadoCompromisso = selecionadoCompromisso;
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
    
    
    

 

}
