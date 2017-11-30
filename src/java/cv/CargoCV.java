/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TbCargo;
import dao.TbCargoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "cargoCV")
public class CargoCV implements Converter {
    private TbCargoDAO dao = new TbCargoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbCargo tbCargo;
        if (value == null) {
            tbCargo = null;
        } else {
            tbCargo = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbCargo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbCargo tbCargo = (TbCargo) value;
            id = tbCargo.getIdtCargo();
        }
        return String.valueOf(id);
    }
}

