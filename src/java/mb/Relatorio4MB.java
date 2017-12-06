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
public class Relatorio4MB {
    private String txtPauta;

    public String getTxtPauta() {
        return txtPauta;
    }

    public void setTxtPauta(String txtPauta) {
        this.txtPauta = txtPauta;
    }
    
    /**
     * Creates a new instance of RelSimples
     */
    public Relatorio4MB() {
    }

    public void imprimir() {
        Report rep = new Report();
        HashMap par=new HashMap();
        par.put("pTxt", txtPauta);
        rep.impRelPDFJasper("rel_pauta.jasper", par, FacesContext.getCurrentInstance());
    }
}
