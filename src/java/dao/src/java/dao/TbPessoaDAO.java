/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Iterator;
import java.util.List;
import model.TbPessoa;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author ra21490034
 */
public class TbPessoaDAO extends BaseDAO <TbPessoa> {
    
     public List<TbPessoa> consultarPorNme(Integer idtPessoa, String nmePessoa) {
        List<TbPessoa> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbPessoa obj "
                + "WHERE (0=? OR TbPessoa.idtPessoa=?) AND nmePessoa LIKE ? "
                + "ORDER BY TbPessoa.nmePessoa");
        qy.setInteger(0, idtPessoa);
        qy.setString(1, "%" + nmePessoa + "%");
        lista = qy.list();
        return lista;
    }
     
         
    public boolean excluirPorIdt(int idtPessoa) {

        // Recuperando todos os registro do relacionamento da versão do parâmetro
        List<TbPessoa> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbPessoa obj WHERE TbPessoa.idtPessoa=?");
        qy.setInteger(0, idtPessoa);
        lista = qy.list();

        // Excluindo todas as associações de cor da versão
        Transaction ts = hib.beginTransaction();
        ts.begin();
        for (Iterator<TbPessoa> it = lista.iterator(); it.hasNext();) {
            TbPessoa tbpessoa = it.next();
            hib.delete(tbpessoa);
        }
        hib.flush();
        ts.commit();
        return true;
    }
    
     
     
    
}
