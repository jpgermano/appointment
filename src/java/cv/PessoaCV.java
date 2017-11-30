/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TbPessoa;
import dao.TbPessoaDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "pessoaCV")
public class PessoaCV implements Converter {
    private TbPessoaDAO dao = new TbPessoaDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbPessoa tbPessoa;
        if (value == null) {
            tbPessoa = null;
        } else {
            tbPessoa = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return tbPessoa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TbPessoa tbPessoa = (TbPessoa) value;
            id = tbPessoa.getIdtPessoa();
        }
        return String.valueOf(id);
    }
}

