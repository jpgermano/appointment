/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TbSetor;
import dao.TbSetorDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "setorCV")
public class SetorCV implements Converter {
    private TbSetorDAO dao = new TbSetorDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbSetor tbSetor;
        if (value == null) {
            tbSetor = null;
        } else {
            tbSetor = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbSetor;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbSetor tbSetor = (TbSetor) value;
            id = tbSetor.getIdtSetor();
        }
        return String.valueOf(id);
    }
}

