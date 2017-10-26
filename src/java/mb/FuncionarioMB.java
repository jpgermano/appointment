/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TaFuncionarioDAO;
import java.util.List;
import model.TaFuncionario;
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
public class FuncionarioMB {

    private TaFuncionario selecionado;
    private List<TaFuncionario> TaFuncionarios;
    private String nmeFuncionario;
    
    /**
     * Creates a new instance of ProdutoMB
     */
    public FuncionarioMB() {
        selecionado = new TaFuncionario();
        nmeFuncionario="";
        filtrar();
    }

    public void filtrar() {
        TaFuncionarioDAO dao = new TaFuncionarioDAO();
        setTaFuncionarios(dao.consultarPorNme(getnmeFuncionario()));
    }

    public void novo() {
        setSelecionado(new TaFuncionario());
        getSelecionado().setIdtFuncionario(0);
        nmeFuncionario="";
    }

    public void salvar() {
        TaFuncionarioDAO dao = new TaFuncionarioDAO();
        if (getSelecionado().getIdtFuncionario()== 0) {
            getSelecionado().setIdtFuncionario(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TaFuncionarioDAO dao = new TaFuncionarioDAO();
        if (getSelecionado().getIdtFuncionario() != 0) {
            if (dao.excluir(getSelecionado().getIdtFuncionario())) {
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
    public TaFuncionario getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TaFuncionario selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmeFuncionario
     */
    public String getnmeFuncionario() {
        return nmeFuncionario;
    }

    /**
     * @param nmeFuncionario the nmeFuncionario to set
     */
    public void setnmeFuncionario(String nmeFuncionario) {
        this.nmeFuncionario = nmeFuncionario;
    }

    /**
     * @return the TaFuncionarios
     */
    public List<TaFuncionario> getTaFuncionarios() {
        return TaFuncionarios;
    }

    /**
     * @param TaFuncionarios the TaFuncionarios to set
     */
    public void setTaFuncionarios(List<TaFuncionario> TaFuncionarios) {
        this.TaFuncionarios = TaFuncionarios;
    }

}
