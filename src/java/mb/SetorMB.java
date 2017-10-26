/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbSetorDAO;
import java.util.List;
import model.TbSetor;
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
public class SetorMB {

    private TbSetor selecionado;
    private List<TbSetor> tbSetores;
    private String nmeSetor;
    
    /**
     * Creates a new instance of SetorMB
     */
    public SetorMB() {
        selecionado = new TbSetor();
        nmeSetor="";
        filtrar();
    }

    public void filtrar() {
        TbSetorDAO dao = new TbSetorDAO();
        setTbSetores(dao.consultarPorNme(getnmeSetor()));
    }

    public void novo() {
        setSelecionado(new TbSetor());
        getSelecionado().setIdtSetor(0);
        nmeSetor="";
    }

    public void salvar() {
        TbSetorDAO dao = new TbSetorDAO();
        if (getSelecionado().getIdtSetor() == 0) {
            getSelecionado().setIdtSetor(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbSetorDAO dao = new TbSetorDAO();
        if (getSelecionado().getIdtSetor() != 0) {
            if (dao.excluir(getSelecionado().getIdtSetor())) {
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
    public TbSetor getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbSetor selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmeSetor
     */
    public String getnmeSetor() {
        return nmeSetor;
    }

    /**
     * @param nmeSetor the nmeSetor to set
     */
    public void setnmeSetor(String nmeSetor) {
        this.nmeSetor = nmeSetor;
    }

    /**
     * @return the tbSetores
     */
    public List<TbSetor> getTbSetores() {
        return tbSetores;
    }

    /**
     * @param tbSetores the tbSetores to set
     */
    public void setTbSetores(List<TbSetor> tbSetores) {
        this.tbSetores = tbSetores;
    }

}
