/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import util.Report;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author fevonlopes
 */
@ManagedBean
@RequestScoped
public class Relatorio1MB {
    
    /**
     * Creates a new instance of RelSimples
     */
    public Relatorio1MB() {
    }

    public void imprimir() {
        Report rep = new Report();
        HashMap par=new HashMap();
        rep.impRelPDFJasper("ata_reuniao.jasper", par, FacesContext.getCurrentInstance());
    }

}
