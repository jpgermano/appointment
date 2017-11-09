/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbCargoDAO;
import java.util.List;
import model.TbCargo;
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
public class CargoMB {

    private TbCargo selecionado;
    private List<TbCargo> tbCargos;
    private String nmeCargo;
    
    /**
     * Creates a new instance of CargoMB
     */
    public CargoMB() {
        selecionado = new TbCargo();
        nmeCargo="";
        filtrar();
    }

    public void filtrar() {
        TbCargoDAO dao = new TbCargoDAO();
        setTbCargos(dao.consultarPorNme(getnmeCargo()));
    }

    public void novo() {
        setSelecionado(new TbCargo());
        getSelecionado().setIdtCargo(0);
        nmeCargo="";
    }

    public void salvar() {
        TbCargoDAO dao = new TbCargoDAO();
        if (getSelecionado().getIdtCargo() == 0) {
            getSelecionado().setIdtCargo(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbCargoDAO dao = new TbCargoDAO();
        if (getSelecionado().getIdtCargo() != 0) {
            if (dao.excluir(getSelecionado().getIdtCargo())) {
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
    public TbCargo getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbCargo selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmeCargo
     */
    public String getnmeCargo() {
        return nmeCargo;
    }

    /**
     * @param nmeCargo the nmeCargo to set
     */
    public void setnmeCargo(String nmeCargo) {
        this.nmeCargo = nmeCargo;
    }

    /**
     * @return the tbCargos
     */
    public List<TbCargo> getTbCargos() {
        return tbCargos;
    }

    /**
     * @param tbCargos the tbCargos to set
     */
    public void setTbCargos(List<TbCargo> tbCargos) {
        this.tbCargos = tbCargos;
    }

}
