/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbPessoaDAO;
import java.util.List;
import model.TbPessoa;
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
public class PessoaMB {

    private TbPessoa selecionado;
    private List<TbPessoa> TbPessoas;
    private String nmePessoa;
    
    /**
     * Creates a new instance of PessoaMB
     */
    public PessoaMB() {
        selecionado = new TbPessoa();
        nmePessoa="";
        filtrar();
    }

    public void filtrar() {
        TbPessoaDAO dao = new TbPessoaDAO();
        setTbPessoas(dao.consultarPorNme(getnmePessoa()));
    }

    public void novo() {
        setSelecionado(new TbPessoa());
        getSelecionado().setIdtPessoa(0);
        nmePessoa="";
    }

    public void salvar() {
        TbPessoaDAO dao = new TbPessoaDAO();
        if (getSelecionado().getIdtPessoa() == 0) {
            getSelecionado().setIdtPessoa(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbPessoaDAO dao = new TbPessoaDAO();
        if (getSelecionado().getIdtPessoa() != 0) {
            if (dao.excluir(getSelecionado().getIdtPessoa())) {
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
    public TbPessoa getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbPessoa selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmePessoa
     */
    public String getnmePessoa() {
        return nmePessoa;
    }

    /**
     * @param nmePessoa the nmePessoa to set
     */
    public void setnmePessoa(String nmePessoa) {
        this.nmePessoa = nmePessoa;
    }

    /**
     * @return the TbPessoas
     */
    public List<TbPessoa> getTbPessoas() {
        return TbPessoas;
    }

    /**
     * @param TbPessoas the TbPessoas to set
     */
    public void setTbPessoas(List<TbPessoa> TbPessoas) {
        this.TbPessoas = TbPessoas;
    }

}
