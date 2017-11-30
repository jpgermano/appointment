/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TbProjeto;
import dao.TbProjetoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "projetoCV")
public class ProjetoCV implements Converter {
    private TbProjetoDAO dao = new TbProjetoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbProjeto tbProjeto;
        if (value == null) {
            tbProjeto = null;
        } else {
            tbProjeto = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbProjeto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbProjeto tbProjeto = (TbProjeto) value;
            id = tbProjeto.getIdtProjeto();
        }
        return String.valueOf(id);
    }
}

