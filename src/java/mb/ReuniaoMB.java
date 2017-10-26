/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbReuniaoDAO;
import java.util.List;
import model.TbReuniao;
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
    private List<TbReuniao> tbReunioes;
    private String nmeLocalReuniao;
    
    /**
     * Creates a new instance of ReuniaoMB
     */
    public ReuniaoMB() {
        selecionado = new TbReuniao();
        nmeLocalReuniao="";
        filtrar();
    }

    public void filtrar() {
        TbReuniaoDAO dao = new TbReuniaoDAO();
        setTbReunioes(dao.consultarPorNmeLocal(getnmeLocalReuniao()));
    }

    public void novo() {
        setSelecionado(new TbReuniao());
        getSelecionado().setIdtReuniao(0);
        nmeLocalReuniao="";
    }

    public void salvar() {
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
     * @return the nmeLocalReuniao
     */
    public String getnmeLocalReuniao() {
        return nmeLocalReuniao;
    }

    /**
     * @param nmeLocalReuniao the nmeLocalReuniao to set
     */
    public void setnmeLocalReuniao(String nmeLocalReuniao) {
        this.nmeLocalReuniao = nmeLocalReuniao;
    }

    /**
     * @return the tbReunioes
     */
    public List<TbReuniao> getTbReunioes() {
        return tbReunioes;
    }

    /**
     * @param tbReunioes the tbReunioess to set
     */
    public void setTbReunioes(List<TbReuniao> tbReunioes) {
        this.tbReunioes = tbReunioes;
    }

}
