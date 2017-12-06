/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import util.Report;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 */
@ManagedBean
@RequestScoped
public class Relatorio2MB {
    private String nmePessoa;

    public String getNmePessoa() {
        return nmePessoa;
    }

    public void setNmePessoa(String nmePessoa) {
        this.nmePessoa = nmePessoa;
    }
    
    /**
     * Creates a new instance of RelSimples
     */
    public Relatorio2MB() {
    }

    public void imprimir() {
        Report rep = new Report();
        HashMap par=new HashMap();
        par.put("pNme", nmePessoa);
        rep.impRelPDFJasper("rel_funcionario.jasper", par, FacesContext.getCurrentInstance());
    }
}
