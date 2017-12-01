/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.*;
import java.util.List;
import model.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    private String nmePessoa;
    private List<TbCargo> tbCargos;
    private Integer idtCargo; // Usado na combo de novo e edição
    private Integer idtCargoPar; // Usado na combo de parâmetros de consulta
    private List<TbEmpresa> tbEmpresas;
    private Integer idtEmpresa; // Usado na combo de novo e edição
    private Integer idtEmpresaPar; // Usado na combo de parâmetros de consulta
    private List<TbPessoa> tbPessoas;
    private Integer idtPessoa; // Usado na combo de novo e edição
    private Integer idtPessoaPar; // Usado na combo de parâmetros de consulta
    private List<TbSetor> tbSetores;
    private Integer idtSetor; // Usado na combo de novo e edição
    private Integer idtSetorPar; // Usado na combo de parâmetros de consulta
    

    
    /**
     * Creates a new instance of ProdutoMB
     */
    public FuncionarioMB() {
        selecionado = new TaFuncionario();
        
        idtCargoPar = 0;
        TbCargoDAO dao = new TbCargoDAO();
        tbCargos = dao.consultarTodos();
        
        idtEmpresaPar = 0;
        TbEmpresaDAO tbEmpresaDAO = new TbEmpresaDAO();
        tbEmpresas = tbEmpresaDAO.consultarTodos();
        
        idtPessoaPar = 0;
        TbPessoaDAO tbPessoaDAO = new TbPessoaDAO();
        tbPessoas = tbPessoaDAO.consultarTodos();
        
        idtSetorPar = 0;
        TbSetorDAO tbSetorDAO = new TbSetorDAO();
        tbSetores = tbSetorDAO.consultarTodos();
        
        filtrar();
    }

    public void filtrar() {
        TaFuncionarioDAO dao = new TaFuncionarioDAO();
        setTaFuncionarios(dao.consultarPorPessoa(getIdtPessoaPar()));
    }

    public void novo() {
        setSelecionado(new TaFuncionario());
        getSelecionado().setIdtFuncionario(0);
        getSelecionado().setFlgAtivo(true);


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

    /**
     * @return the nmePessoa
     */
    public String getNmePessoa() {
        return nmePessoa;
    }

    /**
     * @param nmePessoa the nmePessoa to set
     */
    public void setNmePessoa(String nmePessoa) {
        this.nmePessoa = nmePessoa;
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

    /**
     * @return the idtCargo
     */
    public Integer getIdtCargo() {
        return idtCargo;
    }

    /**
     * @param idtCargo the idtCargo to set
     */
    public void setIdtCargo(Integer idtCargo) {
        this.idtCargo = idtCargo;
    }

    /**
     * @return the idtCargoPar
     */
    public Integer getIdtCargoPar() {
        return idtCargoPar;
    }

    /**
     * @param idtCargoPar the idtCargoPar to set
     */
    public void setIdtCargoPar(Integer idtCargoPar) {
        this.idtCargoPar = idtCargoPar;
    }

    /**
     * @return the tbEmpresas
     */
    public List<TbEmpresa> getTbEmpresas() {
        return tbEmpresas;
    }

    /**
     * @param tbEmpresas the tbEmpresas to set
     */
    public void setTbEmpresas(List<TbEmpresa> tbEmpresas) {
        this.tbEmpresas = tbEmpresas;
    }

    /**
     * @return the idtEmpresa
     */
    public Integer getIdtEmpresa() {
        return idtEmpresa;
    }

    /**
     * @param idtEmpresa the idtEmpresa to set
     */
    public void setIdtEmpresa(Integer idtEmpresa) {
        this.idtEmpresa = idtEmpresa;
    }

    /**
     * @return the idtEmpresaPar
     */
    public Integer getIdtEmpresaPar() {
        return idtEmpresaPar;
    }

    /**
     * @param idtEmpresaPar the idtEmpresaPar to set
     */
    public void setIdtEmpresaPar(Integer idtEmpresaPar) {
        this.idtEmpresaPar = idtEmpresaPar;
    }

    /**
     * @return the tbPessoas
     */
    public List<TbPessoa> getTbPessoas() {
        return tbPessoas;
    }

    /**
     * @param tbPessoas the tbPessoas to set
     */
    public void setTbPessoas(List<TbPessoa> tbPessoas) {
        this.tbPessoas = tbPessoas;
    }

    /**
     * @return the idtPessoa
     */
    public Integer getIdtPessoa() {
        return idtPessoa;
    }

    /**
     * @param idtPessoa the idtPessoa to set
     */
    public void setIdtPessoa(Integer idtPessoa) {
        this.idtPessoa = idtPessoa;
    }

    /**
     * @return the idtPessoaPar
     */
    public Integer getIdtPessoaPar() {
        return idtPessoaPar;
    }

    /**
     * @param idtPessoaPar the idtPessoaPar to set
     */
    public void setIdtPessoaPar(Integer idtPessoaPar) {
        this.idtPessoaPar = idtPessoaPar;
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

    /**
     * @return the idtSetor
     */
    public Integer getIdtSetor() {
        return idtSetor;
    }

    /**
     * @param idtSetor the idtSetor to set
     */
    public void setIdtSetor(Integer idtSetor) {
        this.idtSetor = idtSetor;
    }

    /**
     * @return the idtSetorPar
     */
    public Integer getIdtSetorPar() {
        return idtSetorPar;
    }

    /**
     * @param idtSetorPar the idtSetorPar to set
     */
    public void setIdtSetorPar(Integer idtSetorPar) {
        this.idtSetorPar = idtSetorPar;
    }



    
 

}
