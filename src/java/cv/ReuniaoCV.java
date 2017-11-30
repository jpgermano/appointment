/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TbReuniao;
import dao.TbReuniaoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "reuniaoCV")
public class ReuniaoCV implements Converter {
    private TbReuniaoDAO dao = new TbReuniaoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbReuniao tbReuniao;
        if (value == null) {
            tbReuniao = null;
        } else {
            tbReuniao = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbReuniao;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbReuniao tbReuniao = (TbReuniao) value;
            id = tbReuniao.getIdtReuniao();
        }
        return String.valueOf(id);
    }
}

