/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TbPautaDAO;
import dao.TbReuniaoDAO;
import java.util.List;
import model.TbPauta;
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
public class PautaMB {

    private TbPauta selecionado;
    private List<TbPauta> TbPautas;
    private Integer idtPauta;
    private Integer idtReuniaoPar;
    private List<TbReuniao> tbReunioes;
    private Integer idtReuniao;
    
    /**
     * Creates a new instance of PautaMB
     */
    public PautaMB() {
        selecionado = new TbPauta();
        idtPauta = 0;
        idtReuniaoPar = 0;
        TbReuniaoDAO dao = new TbReuniaoDAO();
        tbReunioes = dao.consultarTodos();
        filtrar();
    }

    public void filtrar() {
        TbPautaDAO dao = new TbPautaDAO();
        setTbPautas(dao.consultarPorReuniaoIdt(getIdtReuniao(), idtPauta));
    }

    public void novo() {
        setSelecionado(new TbPauta());
        getSelecionado().setIdtPauta(0);
        idtReuniao=0;
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
        this.selecionado = selecionado;
    }



    /**
     * @return the TbPautas
     */
    public List<TbPauta> getTbPautas() {
        return TbPautas;
    }

    /**
     * @param TbPautas the TbPautas to set
     */
    public void setTbPautas(List<TbPauta> TbPautas) {
        this.TbPautas = TbPautas;
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

}
