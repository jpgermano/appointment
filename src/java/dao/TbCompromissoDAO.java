/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TbCompromisso;
import org.hibernate.Query;

/**
 *
 * @author hiragi
 */
public class TbCompromissoDAO extends BaseDAO<TbCompromisso> {
            public List<TbCompromisso> consultarCompromissoPorAssunto(Integer idtAssunto, String dscCompromisso) {
        List<TbCompromisso> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbCompromisso obj "
                + "WHERE (0=? OR tbAssunto.idtAssunto=?) AND dscCompromisso LIKE ?"
                + "ORDER BY tbAssunto.txtAssunto, dscCompromisso");
        qy.setInteger(0, idtAssunto);
        qy.setInteger(1, idtAssunto);
        qy.setString(2, "%" + dscCompromisso + "%");
        lista = qy.list();
        return lista;
    }
}
