/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TaFuncionarioDAO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.TaFuncionario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.TaParticipantes;
import model.TbCargo;
import model.TbEmpresa;
import model.TbPessoa;
import model.TbSetor;


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
     private Integer idtFuncionario;
     private TbCargo tbCargo;
     private TbEmpresa tbEmpresa;
     private TbPessoa tbPessoa;
     private TbSetor tbSetor;
     private boolean flgAtivo;
     private String lgnFuncionario;
     private String pswFuncionario;
     private Set<TaParticipantes> taParticipanteses = new HashSet<TaParticipantes>(0);
    
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
        setTaFuncionarios(dao.consultarTodos());
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

    public String getNmeFuncionario() {
        return nmeFuncionario;
    }

    public void setNmeFuncionario(String nmeFuncionario) {
        this.nmeFuncionario = nmeFuncionario;
    }

    public Integer getIdtFuncionario() {
        return idtFuncionario;
    }

    public void setIdtFuncionario(Integer idtFuncionario) {
        this.idtFuncionario = idtFuncionario;
    }

    public TbCargo getTbCargo() {
        return tbCargo;
    }

    public void setTbCargo(TbCargo tbCargo) {
        this.tbCargo = tbCargo;
    }

    public TbEmpresa getTbEmpresa() {
        return tbEmpresa;
    }

    public void setTbEmpresa(TbEmpresa tbEmpresa) {
        this.tbEmpresa = tbEmpresa;
    }

    public TbPessoa getTbPessoa() {
        return tbPessoa;
    }

    public void setTbPessoa(TbPessoa tbPessoa) {
        this.tbPessoa = tbPessoa;
    }

    public TbSetor getTbSetor() {
        return tbSetor;
    }

    public void setTbSetor(TbSetor tbSetor) {
        this.tbSetor = tbSetor;
    }

    public boolean isFlgAtivo() {
        return flgAtivo;
    }

    public void setFlgAtivo(boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    public String getLgnFuncionario() {
        return lgnFuncionario;
    }

    public void setLgnFuncionario(String lgnFuncionario) {
        this.lgnFuncionario = lgnFuncionario;
    }

    public String getPswFuncionario() {
        return pswFuncionario;
    }

    public void setPswFuncionario(String pswFuncionario) {
        this.pswFuncionario = pswFuncionario;
    }

    public Set<TaParticipantes> getTaParticipanteses() {
        return taParticipanteses;
    }

    public void setTaParticipanteses(Set<TaParticipantes> taParticipanteses) {
        this.taParticipanteses = taParticipanteses;
    }

}
