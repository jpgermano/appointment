/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TbReuniao;
import org.hibernate.Query;

/**
 *
 * @author hiragi
 */
public class TbReuniaoDAO extends BaseDAO<TbReuniao> {
    
        public List<TbReuniao> consultarPorNmeLocal(String nmeLocal) {
        List<TbReuniao> lista;
        Query qy = hib.createQuery("SELECT obj FROM " + getClasse().getSimpleName() + " obj WHERE nmeLocal" + getClasse().getSimpleName().substring(2) + " LIKE ?");
        qy.setString(0, "%" + nmeLocal + "%");
        lista = qy.list();
        return lista;
    }
    
}
