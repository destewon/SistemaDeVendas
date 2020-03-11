package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.dao.ContatoDAO;
import br.com.foursys.vendas.model.Contato;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dmunhoz
 */
public class ContatoController {

    public Contato buscarPorCodigo(int id) {
        ContatoDAO dao = new ContatoDAO();
        Contato contato = new Contato();
        try {
            contato = dao.buscarPorCodigo(id);
        } catch (Exception ex) {
            Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contato;
    }
    
    public void updateContato(Object objeto) {
        try {
            new ContatoDAO().salvar(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir endereço!");
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirContato(Object objeto) {
        try {
            new ContatoDAO().excluir(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir endereço!");
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
