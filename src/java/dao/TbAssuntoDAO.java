/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TbAssunto;
import model.TbAssunto;
import org.hibernate.Query;

/**
 *
 * @author hiragi
 */
public class TbAssuntoDAO extends BaseDAO<TbAssunto> {
        public List<TbAssunto> consultarPorReuniaoTxt(Integer idtReuniao, String txtAssunto) {
        List<TbAssunto> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbAssunto obj "
                + "WHERE (0=? OR tbReuniao.idtReuniao=?) AND txtAssunto LIKE ?"
                + "ORDER BY tbReuniao.idtReuniao, txtAssunto");
        qy.setInteger(0, idtReuniao);
        qy.setInteger(1, idtReuniao);
        qy.setString(2, "%" + txtAssunto + "%");
        lista = qy.list();
        return lista;
    }
    public List<TbAssunto> consultarPorPautaAssunto(Integer idtPauta, String txtAssunto) {
        List<TbAssunto> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbAssunto obj "
                + "WHERE (0=? OR tbPauta.idtPauta=?) AND txtAssunto LIKE ?"
                + "ORDER BY tbPauta.txtPauta, txtAssunto");
        qy.setInteger(0, idtPauta);
        qy.setInteger(1, idtPauta);
        qy.setString(2, "%" + txtAssunto + "%");
        lista = qy.list();
        return lista;
    }


    public List<TbAssunto> consultarPorReuniao(Integer idtReuniao) {
        List<TbAssunto> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbAssunto obj WHERE (0=? OR tbReuniao.idtReuniao=?) ORDER BY tbReuniao.nmeLocalReuniao");
        qy.setInteger(0, idtReuniao);
        qy.setInteger(1, idtReuniao);
        lista = qy.list();
        return lista;
    }
}
