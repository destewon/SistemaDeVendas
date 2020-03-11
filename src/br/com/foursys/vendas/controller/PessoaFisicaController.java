package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.dao.PessoaFisicaDAO;
import br.com.foursys.vendas.model.PessoaFisica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dmunhoz
 */
public class PessoaFisicaController {

    public PessoaFisica buscarPorCodigo(int id) {
        PessoaFisicaDAO dao = new PessoaFisicaDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();
        try {
            pessoaFisica = dao.buscarPorCodigo(id);
        } catch (Exception ex) {
            Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoaFisica;
    }
    
    public void updatePessoaFisica(Object objeto) {
        try {
            new PessoaFisicaDAO().salvar(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar pessoa fisica!");
            Logger.getLogger(PessoaFisicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirPessoaFisica(Object objeto) {
        try {
            new PessoaFisicaDAO().excluir(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pessoa fisica!");
            Logger.getLogger(PessoaFisicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
