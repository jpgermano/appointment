/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniceub.fsw.mb;

import dao.TbEmpresaDAO;
import model.TbEmpresa;
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
public class EmpresaMB {

    private TbEmpresa selecionado;
    private String nmeEmpresa;
    
    /**
     * Creates a new instance of ProdutoMB
     */
    public EmpresaMB() {
        selecionado = new TbEmpresa();
        nmeEmpresa="";
        filtrar();
    }

    public void filtrar() {
        TbEmpresaDAO dao = new TbEmpresaDAO();
        setTbEmpresas(dao.consultarPorNme(getnmeEmpresa()));
    }

    public void novo() {
        setSelecionado(new TbEmpresa());
        getSelecionado().setIdtEmpresa(0);
        nmeEmpresa="";
    }

    public void salvar() {
        TbEmpresaDAO dao = new TbEmpresaDAO();
        if (getSelecionado().getIdtEmpresa() == 0) {
            getSelecionado().setIdtEmpresa(null);
            dao.incluir(getSelecionado());
        } else {
            dao.juntar(getSelecionado());
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filtrar();
    }

    public void excluir() {
        TbEmpresaDAO dao = new TbEmpresaDAO();
        if (getSelecionado().getIdtEmpresa() != 0) {
            if (dao.excluir(getSelecionado().getIdtEmpresa())) {
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
    public TbEmpresa getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(TbEmpresa selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the nmeEmpresa
     */
    public String getnmeEmpresa() {
        return nmeEmpresa;
    }

    /**
     * @param nmeEmpresa the nmeEmpresa to set
     */
    public void setnmeEmpresa(String nmeEmpresa) {
        this.nmeEmpresa = nmeEmpresa;
    }

}
