/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TaFuncionario;
import model.TbPessoa;
import org.hibernate.Query;

/**
 *
 * @author Gilberto
 */
public class TaFuncionarioDAO extends BaseDAO<TaFuncionario> {

    public TaFuncionario consultarPorLogin(String login, String senha) {
        TaFuncionario obj;
        Query qy = hib.createQuery("SELECT obj FROM TaFuncionario obj WHERE lgnFuncionario=? AND pswFuncionario=MD5(?)");
        qy.setString(0, login);
        qy.setString(1, senha);
        obj = (TaFuncionario) qy.uniqueResult();
        return obj;
    }
    
    public List<TaFuncionario> consultarPorPessoa(Integer idtPessoa) {
        List<TaFuncionario> lista;
        Query qy = hib.createQuery("SELECT obj FROM TaFuncionario obj WHERE (0=? OR tbPessoa.idtPessoa=?) ORDER BY tbPessoa.nmePessoa");
        qy.setInteger(0, idtPessoa);
        qy.setInteger(1, idtPessoa);
        lista = qy.list();
        return lista;
    }
}
