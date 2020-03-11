package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.dao.PessoaJuridicaDAO;
import br.com.foursys.vendas.model.PessoaJuridica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dmunhoz
 */
public class PessoaJuridicaController {

    public PessoaJuridica buscarPorCodigo(int id) {
        PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        try {
            pessoaJuridica = dao.buscarPorCodigo(id);
        } catch (Exception ex) {
            Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoaJuridica;
    }
    
    public void updatePessoaJuridica(Object objeto) {
        try {
            new PessoaJuridicaDAO().salvar(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir pessoa jurídica!");
            Logger.getLogger(PessoaJuridicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirPessoaJuridica(Object objeto) {
        try {
            new PessoaJuridicaDAO().excluir(objeto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pessoa jurídica!");
            Logger.getLogger(PessoaJuridicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
