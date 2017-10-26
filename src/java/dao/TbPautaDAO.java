/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TbPauta;
import org.hibernate.Query;

/**
 *
 * @author hiragi
 */
public class TbPautaDAO extends BaseDAO<TbPauta> {
    
        public List<TbPauta> consultarPorReuniaoIdt(Integer idtReuniao, Integer idtPauta) {
        List<TbPauta> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbPauta obj "
                + "WHERE (0=? OR tbReuniao.idtReuniao=?) AND idtPauta (0=? OR tbPauta.idtPauta=?)"
                + "ORDER BY tbReuniao.nmeLocalReuniao, idtPauta");
        qy.setInteger(0, idtReuniao);
        qy.setInteger(1, idtReuniao);
        qy.setInteger(2, idtPauta);
        lista = qy.list();
        return lista;
    }

    public List<TbPauta> consultarPorReuniao(Integer idtReuniao) {
        List<TbPauta> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbReuniao obj WHERE (0=? OR tbReuniao.idtReuniao=?) ORDER BY tbReuniao.nmeLocalReuniao");
        qy.setInteger(0, idtReuniao);
        qy.setInteger(1, idtReuniao);
        lista = qy.list();
        return lista;
    }
    
    
}
