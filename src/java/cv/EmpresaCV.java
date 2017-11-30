/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TbEmpresa;
import dao.TbEmpresaDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "empresaCV")
public class EmpresaCV implements Converter {
    private TbEmpresaDAO dao = new TbEmpresaDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbEmpresa tbEmpresa;
        if (value == null) {
            tbEmpresa = null;
        } else {
            tbEmpresa = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbEmpresa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbEmpresa tbEmpresa = (TbEmpresa) value;
            id = tbEmpresa.getIdtEmpresa();
        }
        return String.valueOf(id);
    }
}

