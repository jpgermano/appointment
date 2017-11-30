/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TaParticipantes;
import org.hibernate.Query;

/**
 *
 * @author hiragi
 */
public class TaParticipantesDAO extends BaseDAO<TaParticipantes> {
        public List<TaParticipantes> consultarParticipantesPorReuniao(Integer idtReuniao) {
        List<TaParticipantes> lista;
        Query qy = hib.createQuery("SELECT obj FROM TaParticipantes obj "
                + "WHERE (0=? OR tbReuniao.idtReuniao=?)"
                + "ORDER BY tbReuniao.nmeLocalReuniao");
        qy.setInteger(0, idtReuniao);
        qy.setInteger(1, idtReuniao);
        lista = qy.list();
        return lista;
    }
}
