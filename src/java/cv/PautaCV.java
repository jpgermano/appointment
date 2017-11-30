/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import dao.TbPautaDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.TbPauta;
/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "pautaCV")
public class PautaCV implements Converter {
    private TbPautaDAO dao = new TbPautaDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbPauta tbPauta;
        if (value == null) {
            tbPauta = null;
        } else {
            tbPauta = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbPauta;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbPauta tbPauta = (TbPauta) value;
            id = tbPauta.getIdtPauta();
        }
        return String.valueOf(id);
    }
}

