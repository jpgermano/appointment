/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.*;
import java.util.List;
import model.*;
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
public class ParticipantesMB {

    private TaParticipantes selecionado;
    private List<TaParticipantes> TaParticipantess;
    private List<TbFuncao> tbFuncoes;
    private Integer idtFuncao; // Usado na combo de novo e edição
    private List<TbReuniao> tbReunioes;
    private Integer idtReuniao; // Usado na combo de novo e edição
    private Integer idtReuniaoPar; 
    private List<TaFuncionario> taFuncionarios;
    private Integer idtFuncionario; // Usado na combo de novo e edição
  

    
    /**
     * Creates a new instance of ProdutoMB
     */
    public ParticipantesMB() {
        selecionado = new TaParticipantes();
       
        idtReuniaoPar = 0;
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReunioes = dao.consultarTodos();
        
        TbFuncaoDAO tbFuncaoDAO = new TbFuncaoDAO();
        tbFuncoes = tbFuncaoDAO.consultarTodos();
        
        TaFuncionarioDAO taFuncionarioDAO = new TaFuncionarioDAO();
        taFuncionarios = taFuncionarioDAO.consultarTodos();
        
        filtrar();
    }

    public void filtrar() {
        TaParticipantesDAO dao = new TaParticipantesDAO();
        setTaParticipantess(dao.consultarParticipantesPorReuniao(getIdtReuniaoPar()));
    }

    public void novo() {
        setSelecionado(new TaParticipantes());
        getSelecionado().setIdtParticipantes(0);
        idtFuncao = 0;
        idtFuncionario = 0;
        idtReuniao =0;

    }

    public void salvar() {   
        TbReuniaoDAO tbReuniaoDAO = new TbReuniaoDAO();
        getSelecionado().setTbReuniao(tbReuniaoDAO.consultarPorIdt(getIdtReuniao()));
        TbFuncaoDAO tbFuncaoDAO = new TbFuncaoDAO();
        getSelecionado().setTbFuncao(tbFuncaoDAO.consultarPorIdt(getIdtFuncao()));
        TaFuncionarioDAO taFuncionarioDAO = new TaFuncionarioDAO();
        getSelecionado().setTaFuncionario(taFuncionarioDAO.consultarPorIdt(getIdtFuncionario()));

        TaParticipantesDAO dao = new TaParticipantesDAO();
        if (getSelecionado().getIdtParticipantes()== 0) {
            getSelecionado().setIdtParticipantes(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TaParticipantesDAO dao = new TaParticipantesDAO();
        if (getSelecionado().getIdtParticipantes() != 0) {
            if (dao.excluir(getSelecionado().getIdtParticipantes())) {
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
    public TaParticipantes getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TaParticipantes selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the TaParticipantess
     */
    public List<TaParticipantes> getTaParticipantess() {
        return TaParticipantess;
    }

    /**
     * @param TaParticipantess the TaParticipantess to set
     */
    public void setTaParticipantess(List<TaParticipantes> TaParticipantess) {
        this.TaParticipantess = TaParticipantess;
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

    


    
 

}
