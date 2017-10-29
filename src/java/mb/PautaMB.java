/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbPautaDAO;
import dao.TbReuniaoDAO;
import model.TbPauta;
import model.TbReuniao;
import java.util.List;
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
public class PautaMB {

    private TbPauta selecionado;
    private List<TbPauta> tbPautas;
    private String txtPauta;
    private Integer idtReuniaoPar; // Usado na combo de parâmetros de consulta
    private List<TbReuniao> tbReuniaos;
    private Integer idtReuniao; // Usado na combo de novo e edição

    /**
     * Creates a new instance of PautaMB
     */
    public PautaMB() {
        selecionado = new TbPauta();
        txtPauta = "";
        idtReuniaoPar = 0;
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReuniaos = dao.consultarTodos();
        filtrar();
    }

    public void filtrar() {
        TbPautaDAO dao = new TbPautaDAO();
        setTbPautas(dao.consultarPorReuniaoTxt(getIdtReuniaoPar(), txtPauta));
    }

    public void novo() {
        setSelecionado(new TbPauta());
        getSelecionado().setIdtPauta(0);
        idtReuniao = 0;
    }

    public void salvar() {
        TbReuniaoDAO tbReuniaoDAO = new TbReuniaoDAO();
        selecionado.setTbReuniao(tbReuniaoDAO.consultarPorIdt(idtReuniao));
        TbPautaDAO dao = new TbPautaDAO();
        if (getSelecionado().getIdtPauta() == 0) {
            getSelecionado().setIdtPauta(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbPautaDAO dao = new TbPautaDAO();
        if (getSelecionado().getIdtPauta() != 0) {
            if (dao.excluir(getSelecionado().getIdtPauta())) {
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
    public TbPauta getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbPauta selecionado) {
        if (selecionado.getTbReuniao() != null) {
            idtReuniao = selecionado.getTbReuniao().getIdtReuniao();
        }
        this.selecionado = selecionado;
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
     * @return the tbReuniaos
     */
    public List<TbReuniao> getTbReuniaos() {
        return tbReuniaos;
    }

    /**
     * @param tbReuniaos the tbReuniaos to set
     */
    public void setTbReuniaos(List<TbReuniao> tbReuniaos) {
        this.tbReuniaos = tbReuniaos;
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
}
