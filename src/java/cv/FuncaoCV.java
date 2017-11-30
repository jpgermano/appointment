/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TbFuncao;
import dao.TbFuncaoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "funcaoCV")
public class FuncaoCV implements Converter {
    private TbFuncaoDAO dao = new TbFuncaoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbFuncao tbFuncao;
        if (value == null) {
            tbFuncao = null;
        } else {
            tbFuncao = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbFuncao;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbFuncao tbFuncao = (TbFuncao) value;
            id = tbFuncao.getIdtFuncao();
        }
        return String.valueOf(id);
    }
}

