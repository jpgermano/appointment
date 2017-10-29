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
    
        public List<TbPauta> consultarPorReuniaoTxt(Integer idtReuniao, String txtPauta) {
        List<TbPauta> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbPauta obj "
                + "WHERE (0=? OR tbReuniao.idtReuniao=?) AND txtPauta LIKE ?"
                + "ORDER BY tbReuniao.nmeLocalReuniao, txtPauta");
        qy.setInteger(0, idtReuniao);
        qy.setInteger(1, idtReuniao);
        qy.setString(2, "%" + txtPauta + "%");
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
