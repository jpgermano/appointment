/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TaFuncionario;
import dao.TaFuncionarioDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "funcionarioCV")
public class FuncionarioCV implements Converter {
    private TaFuncionarioDAO dao = new TaFuncionarioDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TaFuncionario tbFuncionario;
        if (value == null) {
            tbFuncionario = null;
        } else {
            tbFuncionario = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbFuncionario;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TaFuncionario tbFuncionario = (TaFuncionario) value;
            id = tbFuncionario.getIdtFuncionario();
        }
        return String.valueOf(id);
    }
}

