/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv;
import model.TaParticipantes;
import dao.TaParticipantesDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ra21485532
 */
@FacesConverter(value = "participantesCV")
public class ParticipantesCV implements Converter {
    private TaParticipantesDAO dao = new TaParticipantesDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TaParticipantes taParticipantes;
        if (value == null) {
            taParticipantes = null;
        } else {
            taParticipantes = dao.consultarPorIdt(Integer.parseInt(value));
        }
        return taParticipantes;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int id = 0;
        if (value != null) {
            TaParticipantes taParticipantes = (TaParticipantes) value;
            id = taParticipantes.getIdtParticipantes();
        }
        return String.valueOf(id);
    }
}

