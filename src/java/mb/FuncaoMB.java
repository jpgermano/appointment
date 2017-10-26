/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbFuncaoDAO;
import java.util.List;
import model.TbFuncao;
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
public class FuncaoMB {

    private TbFuncao selecionado;
    private List<TbFuncao> TbFuncoes;
    private String nmeFuncao;
    
    /**
     * Creates a new instance of FuncaoMB
     */
    public FuncaoMB() {
        selecionado = new TbFuncao();
        nmeFuncao="";
        filtrar();
    }

    public void filtrar() {
        TbFuncaoDAO dao = new TbFuncaoDAO();
        setTbFuncoes(dao.consultarPorNme(getnmeFuncao()));
    }

    public void novo() {
        setSelecionado(new TbFuncao());
        getSelecionado().setIdtFuncao(0);
        nmeFuncao="";
    }

    public void salvar() {
        TbFuncaoDAO dao = new TbFuncaoDAO();
        if (getSelecionado().getIdtFuncao() == 0) {
            getSelecionado().setIdtFuncao(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbFuncaoDAO dao = new TbFuncaoDAO();
        if (getSelecionado().getIdtFuncao() != 0) {
            if (dao.excluir(getSelecionado().getIdtFuncao())) {
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
    public TbFuncao getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbFuncao selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmeFuncao
     */
    public String getnmeFuncao() {
        return nmeFuncao;
    }

    /**
     * @param nmeFuncao the nmeFuncao to set
     */
    public void setnmeFuncao(String nmeFuncao) {
        this.nmeFuncao = nmeFuncao;
    }

    /**
     * @return the TbFuncoes
     */
    public List<TbFuncao> getTbFuncoes() {
        return TbFuncoes;
    }

    /**
     * @param TbFuncoes the TbFuncoes to set
     */
    public void setTbFuncoes(List<TbFuncao> TbFuncoes) {
        this.TbFuncoes = TbFuncoes;
    }

}
