/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.TaFuncionarioDAO;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.TaFuncionario;

/**
 *
 * @author Gilberto
 */
@ManagedBean
@SessionScoped
public class LoginMB {

    private String login;
    private String senha;
    private String nome;
    private String cargo;

    /**
     * Creates a new instance of ControleAcessoMB
     */
    public LoginMB() {

    }

    public String validar() {
        String ret = "index";
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        TaFuncionarioDAO dao = new TaFuncionarioDAO();
        TaFuncionario funcionario = dao.consultarPorLogin(getLogin(), getSenha());

        if (funcionario == null && cargo == null) {
            setCargo("INVALIDO");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha no login", "Você não tem permissão para entrar no sistema.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (funcionario != null) {
                setCargo("Funcionário Comum");
                setNome(funcionario.getTbPessoa().getNmePessoa());
                ret = "/Funcionario/MenuFuncionario";
            }
         /*   if (funcionario != null && cargo.equals("Programador")) {
                setCargo("Funcionario Administrador");
                setNome(funcionario.getTbPessoa().getNmePessoa());
                ret = "/Funcionario/MenuAdministrador";
            }*/
        }
        sessao.setAttribute("SESSAO_PERFIL", getCargo());
        return ret;
    }

    public void deslogar() throws IOException {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sessao.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        System.out.println("Sucesso");
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
