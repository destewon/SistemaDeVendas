package br.com.foursys.vendas.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.foursys.vendas.dao.FornecedorDAO;
import br.com.foursys.vendas.dao.ProdutoDAO;
import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Produto;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.Estado;
import br.com.foursys.vendas.util.Mensagem;
import br.com.foursys.vendas.model.Fornecedor;
import br.com.foursys.vendas.model.Fornecedor;
import br.com.foursys.vendas.util.Valida;
import br.com.foursys.vendas.view.ProdutoPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dmunhoz
 */
public class ProdutoController {

    private ProdutoPrincipal viewProduto;
    private Produto produto = new Produto();
    private List<Produto> listaProdutos;
    private List<Estado> listaEstados;
    private List<Fornecedor> listaFornecedor;
    private boolean alterar;

    public ProdutoController(ProdutoPrincipal viewProduto) {
        this.viewProduto = viewProduto;
    }

    public void excluirProduto() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewProduto.getTabelaProduto().getModel();
        if (this.viewProduto.getTabelaProduto().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecaoErro);
        } else {
            produto = listaProdutos.get(this.viewProduto.getTabelaProduto().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.confirmaExcluir, "Atenção",
                    JOptionPane.YES_OPTION,
                    JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                ProdutoDAO dao = new ProdutoDAO();
                try {
                    dao.excluir(produto);

                    JOptionPane.showMessageDialog(null, Mensagem.excluidoComSucesso);
                    listarProdutos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroExclusao);
                    Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void listarProdutos() {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            listaProdutos = dao.buscarTodos();
            carregarTabela();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewProduto.getTabelaProduto().getModel();
        modelo.setRowCount(0);
        for (Produto produto : listaProdutos) {

            modelo.addRow(new String[]{produto.getDescricao(), produto.getFornecedorIdFornecedor().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(), produto.getValorCusto() + "", produto.getValorVenda() + ""});

        }
    }

    public void bloqueioInicial() {
        this.viewProduto.getJbtNovo().setEnabled(true);
        this.viewProduto.getJbtAlterar().setEnabled(true);
        this.viewProduto.getJbtExcluir().setEnabled(true);
        this.viewProduto.getJbtSair().setEnabled(true);
        this.viewProduto.getJtfPesquisarDescricao().setEditable(true);
        this.viewProduto.getJbtSalvar().setEnabled(false);
        this.viewProduto.getJbtCancelar().setEnabled(false);
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewProduto.getJtfPesquisarDescricao().grabFocus();

        this.viewProduto.getJtfDescricao().setEditable(false);
        this.viewProduto.getJcbFornecedor().setEnabled(false);

        this.viewProduto.getJtfValorCusto().setEditable(false);
        this.viewProduto.getJtfValorVenda().setEditable(false);

    }

    public void limparCampos() {
        this.viewProduto.getJtfDescricao().setText(null);

        this.viewProduto.getJtfValorCusto().setText(null);

        this.viewProduto.getJtfValorVenda().setText(null);

        this.viewProduto.getJcbFornecedor().setSelectedIndex(0);

    }

    public void acaoBotaoNovo() {
        this.viewProduto.getJbtNovo().setEnabled(false);
        this.viewProduto.getJbtAlterar().setEnabled(false);
        this.viewProduto.getJbtExcluir().setEnabled(false);
        this.viewProduto.getJbtSair().setEnabled(false);
        this.viewProduto.getJbtSalvar().setEnabled(true);
        this.viewProduto.getJbtCancelar().setEnabled(true);
        this.viewProduto.getJtfPesquisarDescricao().setEditable(false);

        liberarCampos();
        this.alterar = false;
    }

    public void liberarCampos() {

        this.viewProduto.getJtfPesquisarDescricao().grabFocus();

        this.viewProduto.getJtfDescricao().setEditable(true);
        this.viewProduto.getJcbFornecedor().setEnabled(true);

        this.viewProduto.getJtfValorCusto().setEditable(true);
        this.viewProduto.getJtfValorVenda().setEditable(true);
    }

    public void acaoBotaoCancelar() {
        this.viewProduto.getJbtNovo().setEnabled(true);
        this.viewProduto.getJbtAlterar().setEnabled(true);
        this.viewProduto.getJbtExcluir().setEnabled(true);
        this.viewProduto.getJbtSair().setEnabled(true);
        this.viewProduto.getJbtSalvar().setEnabled(false);
        this.viewProduto.getJbtCancelar().setEnabled(false);
        this.viewProduto.getJtfPesquisarDescricao().setEditable(true);

        limparCampos();
        bloquearCampos();
    }

    public void carregarComboFornecedor() {
        FornecedorController fornecedor = new FornecedorController();
        try {
            listaFornecedor = fornecedor.buscarFornecedores();
            this.viewProduto.getJcbFornecedor().removeAllItems();
            this.viewProduto.getJcbFornecedor().addItem("- Selecione Fornecedor -");
            for (Fornecedor fornecedor2 : listaFornecedor) {
                this.viewProduto.getJcbFornecedor().addItem(fornecedor2.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarProduto() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                fornecedor = listaFornecedor.get(this.viewProduto.getJcbFornecedor().getSelectedIndex() - 1);
                produto.setFornecedorIdFornecedor(fornecedor);
                

                

                produto.setDescricao(this.viewProduto.getJtfDescricao().getText());
                produto.setValorCusto(Double.parseDouble(this.viewProduto.getJtfValorCusto().getText()));
                produto.setValorVenda(Double.parseDouble(this.viewProduto.getJtfValorVenda().getText()));

                ProdutoDAO dao = new ProdutoDAO();
                try {
                    dao.salvar(produto);
                    JOptionPane.showMessageDialog(null, Mensagem.sucessoInserir);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroInserir);
                    Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarProdutos();
            }
        } else {
            //alterando o registro
            if (validarSalvar()) {

                
                produto.setValorCusto(Double.parseDouble(this.viewProduto.getJtfValorCusto().getText()));
                produto.setValorVenda(Double.parseDouble(this.viewProduto.getJtfValorVenda().getText()));
                
                
                Fornecedor fornecedor = new Fornecedor();
                fornecedor = listaFornecedor.get(this.viewProduto.getJcbFornecedor().getSelectedIndex() - 1);
                produto.setFornecedorIdFornecedor(fornecedor);
                
                ProdutoDAO dao = new ProdutoDAO();
                try {
                    dao.salvar(produto);
                    JOptionPane.showMessageDialog(null, Mensagem.alteradoSucesso);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroAlterar);
                    Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarProdutos();
            }
        }
    }

    public boolean validarSalvar() {

        

        if (Valida.validaString(this.viewProduto.getJtfDescricao().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.descricaoInvalido);
            return false;
        }
        
        if (Valida.validaDouble(this.viewProduto.getJtfValorCusto().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.valorCusto);
            return false;
        }
        
        if (Valida.validaDouble(this.viewProduto.getJtfValorVenda().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.valorVenda);
            return false;
        }

        if (Valida.validaComboBox(this.viewProduto.getJcbFornecedor().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.fornecedorInvalido);
            return false;
        }

        

        return true;
    }

    public void alterarProduto() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewProduto.getTabelaProduto().getModel();
        if (this.viewProduto.getTabelaProduto().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecaoErro);
        } else {
            produto = listaProdutos.get(this.viewProduto.getTabelaProduto().getSelectedRow());

            this.viewProduto.getJtfDescricao().setText(produto.getDescricao());
            this.viewProduto.getJtfValorCusto().setText(produto.getValorCusto()+"");
            this.viewProduto.getJtfValorVenda().setText(produto.getValorVenda()+"");
            this.viewProduto.getJcbFornecedor().setSelectedItem(produto.getFornecedorIdFornecedor().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
            
            this.alterar = true;
            acaoBotaoAlterar();
        }
    }

    public void acaoBotaoAlterar() {
        this.viewProduto.getJbtNovo().setEnabled(false);
        this.viewProduto.getJbtAlterar().setEnabled(false);
        this.viewProduto.getJbtExcluir().setEnabled(false);
        this.viewProduto.getJbtSair().setEnabled(false);
        this.viewProduto.getJtfPesquisarDescricao().setEditable(false);
        
        this.viewProduto.getJbtSalvar().setEnabled(true);
        this.viewProduto.getJbtCancelar().setEnabled(true);
        liberarCampos();

        this.viewProduto.getJtfDescricao().setEditable(false);
        

    }

}
