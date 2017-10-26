/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbProjetoDAO;
import java.util.List;
import model.TbProjeto;
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
public class ProjetoMB {

    private TbProjeto selecionado;
    private List<TbProjeto> tbProjetos;
    private String nmeProjeto;
    
    /**
     * Creates a new instance of ProjetoMB
     */
    public ProjetoMB() {
        selecionado = new TbProjeto();
        nmeProjeto="";
        filtrar();
    }

    public void filtrar() {
        TbProjetoDAO dao = new TbProjetoDAO();
        setTbProjetos(dao.consultarPorNme(getnmeProjeto()));
    }

    public void novo() {
        setSelecionado(new TbProjeto());
        getSelecionado().setIdtProjeto(0);
        nmeProjeto="";
    }

    public void salvar() {
        TbProjetoDAO dao = new TbProjetoDAO();
        if (getSelecionado().getIdtProjeto() == 0) {
            getSelecionado().setIdtProjeto(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbProjetoDAO dao = new TbProjetoDAO();
        if (getSelecionado().getIdtProjeto() != 0) {
            if (dao.excluir(getSelecionado().getIdtProjeto())) {
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
    public TbProjeto getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbProjeto selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmeProjeto
     */
    public String getnmeProjeto() {
        return nmeProjeto;
    }

    /**
     * @param nmeProjeto the nmeProjeto to set
     */
    public void setnmeProjeto(String nmeProjeto) {
        this.nmeProjeto = nmeProjeto;
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

}
