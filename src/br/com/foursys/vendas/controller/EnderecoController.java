package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.dao.EnderecoDAO;
import br.com.foursys.vendas.model.Endereco;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dmunhoz
 */
public class EnderecoController {

    public Endereco buscarPorCodigo(int id) {
        Endereco endereco = new Endereco();
        try {
            endereco = new EnderecoDAO().buscarPorCodigo(id);
        } catch (Exception ex) {
            Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return endereco;
    }
    
    public void updateEndereco(Object objeto) {
        try {
            new EnderecoDAO().salvar(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar endereço!");
            Logger.getLogger(EnderecoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirEndereco(Object objeto) {
        try {
            new EnderecoDAO().excluir(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir endereço!");
            Logger.getLogger(EnderecoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
