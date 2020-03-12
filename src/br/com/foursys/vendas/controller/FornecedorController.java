package br.com.foursys.vendas.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.foursys.vendas.dao.FornecedorDAO;
import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Fornecedor;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.Estado;
import br.com.foursys.vendas.util.Mensagem;
import br.com.foursys.vendas.model.PessoaJuridica;
import br.com.foursys.vendas.model.PessoaJuridica;
import br.com.foursys.vendas.util.Valida;
import br.com.foursys.vendas.view.FornecedorPrincipal;
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
public class FornecedorController {

    private FornecedorPrincipal viewFornecedor;
    private Fornecedor fornecedor = new Fornecedor();
    private List<Fornecedor> listaFornecedors;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private boolean alterar;

    public FornecedorController(FornecedorPrincipal viewFornecedor) {
        this.viewFornecedor = viewFornecedor;
    }

    public FornecedorController() {

    }

    public List<Fornecedor> buscarFornecedores() throws Exception {
        List<Fornecedor> listaFornecedor;
        FornecedorDAO dao = new FornecedorDAO();
        listaFornecedor = dao.buscarTodos();
        return listaFornecedor;
    }

    public void excluirFornecedor() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFornecedor.getTabelaFornecedor().getModel();
        if (this.viewFornecedor.getTabelaFornecedor().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecaoErro);
        } else {
            fornecedor = listaFornecedors.get(this.viewFornecedor.getTabelaFornecedor().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.confirmaExcluir, "Atenção",
                    JOptionPane.YES_OPTION,
                    JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                FornecedorDAO dao = new FornecedorDAO();
                try {
                    dao.excluir(fornecedor);
                    new ContatoController().excluirContato(fornecedor.getContatoIdContato());
                    new EnderecoController().excluirEndereco(fornecedor.getEnderecoIdEndereco());

                    new PessoaJuridicaController().excluirPessoaJuridica(fornecedor.getPessoaJuridicaIdPessoaJuridica());
                    JOptionPane.showMessageDialog(null, Mensagem.excluidoComSucesso);
                    listarFornecedors();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroExclusao);
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void listarFornecedors() {
        try {
            FornecedorDAO dao = new FornecedorDAO();
            listaFornecedors = dao.buscarTodos();
            carregarTabela();
        } catch (Exception ex) {
            Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFornecedor.getTabelaFornecedor().getModel();
        modelo.setRowCount(0);
        for (Fornecedor fornecedor : listaFornecedors) {

            modelo.addRow(new String[]{fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(), fornecedor.getContatoIdContato().getTelefone(), fornecedor.getContato(), fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getNome()});

        }
    }

    public void bloqueioInicial() {
        this.viewFornecedor.getJbtNovo().setEnabled(true);
        this.viewFornecedor.getJbtAlterar().setEnabled(true);
        this.viewFornecedor.getJbtExcluir().setEnabled(true);
        this.viewFornecedor.getJbtSair().setEnabled(true);
        this.viewFornecedor.getJtfPesquisaCNPJ().setEnabled(true);
        this.viewFornecedor.getJbtSalvar().setEnabled(false);
        this.viewFornecedor.getJbtCancelar().setEnabled(false);
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewFornecedor.getJtfPesquisaCNPJ().setEditable(true);
        this.viewFornecedor.getJtfPesquisaRazaoSocial().grabFocus();
        this.viewFornecedor.getJtfCnpj().setEditable(false);
        this.viewFornecedor.getJtfInscricaoEstadual().setEditable(false);
        this.viewFornecedor.getJtfDataFundacao().setEditable(false);
        this.viewFornecedor.getJtfRazaoSocial().setEditable(false);
        this.viewFornecedor.getJtfEndereco().setEditable(false);
        this.viewFornecedor.getJtfNumero().setEditable(false);
        this.viewFornecedor.getJtfBairro().setEditable(false);
        this.viewFornecedor.getJcbCidade().setEnabled(false);
        this.viewFornecedor.getJcbEstado().setEnabled(false);
        this.viewFornecedor.getJtfTelefone().setEditable(false);
        this.viewFornecedor.getJtfDataFundacao().setEditable(false);
        this.viewFornecedor.getJtfCelular().setEditable(false);
        this.viewFornecedor.getJtfEmail().setEditable(false);
        this.viewFornecedor.getJtfContato().setEditable(false);
        this.viewFornecedor.getJtfEmail().setEditable(false);
        this.viewFornecedor.getJtfComplemento().setEditable(false);
        this.viewFornecedor.getJtfCep().setEditable(false);

    }

    public void limparCampos() {
        this.viewFornecedor.getJtfCnpj().setText(null);
        this.viewFornecedor.getJtfCnpj().setValue(null);
        this.viewFornecedor.getJtfInscricaoEstadual().setText(null);
        this.viewFornecedor.getJtfInscricaoEstadual().setValue(null);
        this.viewFornecedor.getJtfDataFundacao().setText(null);
        this.viewFornecedor.getJtfDataFundacao().setValue(null);
        this.viewFornecedor.getJtfRazaoSocial().setText(null);
        this.viewFornecedor.getJtfEndereco().setText(null);
        this.viewFornecedor.getJtfNumero().setText(null);
        this.viewFornecedor.getJtfBairro().setText(null);
        this.viewFornecedor.getJcbCidade().setSelectedIndex(0);
        this.viewFornecedor.getJcbEstado().setSelectedIndex(0);
        this.viewFornecedor.getJtfTelefone().setText(null);
        this.viewFornecedor.getJtfTelefone().setValue(null);
        //this.viewFornecedor.getJtfSenha().setText(null);
        //this.viewFornecedor.getJtfLogin().setText(null);

        this.viewFornecedor.getJtfCelular().setText(null);
        this.viewFornecedor.getJtfCelular().setValue(null);

        this.viewFornecedor.getJtfComplemento().setText(null);
        this.viewFornecedor.getJtfCep().setText(null);
        this.viewFornecedor.getJtfCep().setValue(null);

        this.viewFornecedor.getJtfEmail().setText(null);
    }

    public void acaoBotaoNovo() {
        this.viewFornecedor.getJbtNovo().setEnabled(false);
        this.viewFornecedor.getJbtAlterar().setEnabled(false);
        this.viewFornecedor.getJbtExcluir().setEnabled(false);
        this.viewFornecedor.getJbtSair().setEnabled(false);
        this.viewFornecedor.getJbtSalvar().setEnabled(true);
        this.viewFornecedor.getJbtCancelar().setEnabled(true);
        this.viewFornecedor.getJtfPesquisaCNPJ().setEditable(false);
        this.viewFornecedor.getJtfPesquisaRazaoSocial().setEditable(false);
        liberarCampos();
        this.alterar = false;
    }

    public void liberarCampos() {

        this.viewFornecedor.getJtfCnpj().grabFocus();
        this.viewFornecedor.getJtfCnpj().setEditable(true);
        this.viewFornecedor.getJtfInscricaoEstadual().setEditable(true);
        this.viewFornecedor.getJtfDataFundacao().setEditable(true);
        this.viewFornecedor.getJtfRazaoSocial().setEditable(true);
        this.viewFornecedor.getJtfEndereco().setEditable(true);
        this.viewFornecedor.getJtfNumero().setEditable(true);
        this.viewFornecedor.getJtfBairro().setEditable(true);
        this.viewFornecedor.getJcbCidade().setEnabled(true);
        this.viewFornecedor.getJcbEstado().setEnabled(true);
        this.viewFornecedor.getJtfTelefone().setEditable(true);
        this.viewFornecedor.getJtfDataFundacao().setEditable(true);
        this.viewFornecedor.getJtfCelular().setEditable(true);
        this.viewFornecedor.getJtfEmail().setEditable(true);
        this.viewFornecedor.getJtfContato().setEditable(true);
        this.viewFornecedor.getJtfEmail().setEditable(true);
        this.viewFornecedor.getJtfComplemento().setEditable(true);
        this.viewFornecedor.getJtfCep().setEditable(true);
    }

    public void acaoBotaoCancelar() {
        this.viewFornecedor.getJbtNovo().setEnabled(true);
        this.viewFornecedor.getJbtAlterar().setEnabled(true);
        this.viewFornecedor.getJbtExcluir().setEnabled(true);
        this.viewFornecedor.getJbtSair().setEnabled(true);
        this.viewFornecedor.getJbtSalvar().setEnabled(false);
        this.viewFornecedor.getJbtCancelar().setEnabled(false);
        this.viewFornecedor.getJtfPesquisaRazaoSocial().setEditable(true);
        this.viewFornecedor.getJtfPesquisaCNPJ().setEditable(true);
        limparCampos();
        bloquearCampos();
    }

    public void carregarComboCidade() {
        CidadeController controller = new CidadeController();
        try {
            listaCidades = controller.buscarCidades();
            this.viewFornecedor.getJcbCidade().removeAllItems();
            this.viewFornecedor.getJcbCidade().addItem("- Selecione Cidade -");
            for (Cidade cidade : listaCidades) {
                this.viewFornecedor.getJcbCidade().addItem(cidade.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarComboEstado() {
        EstadoController controller = new EstadoController();
        try {
            listaEstados = controller.buscarEstados();
            this.viewFornecedor.getJcbEstado().removeAllItems();
            this.viewFornecedor.getJcbEstado().addItem("- Selecione Estado -");
            for (Estado estado : listaEstados) {
                this.viewFornecedor.getJcbEstado().addItem(estado.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvarFornecedor() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Fornecedor fornecedor = new Fornecedor();
                PessoaJuridica pessoaJuridica = new PessoaJuridica();

                Endereco endereco = new Endereco();
                Cidade cidade = new Cidade();
                Contato contato = new Contato();

                pessoaJuridica.setRazaoSocial(this.viewFornecedor.getJtfRazaoSocial().getText());
                pessoaJuridica.setCnpj(this.viewFornecedor.getJtfCnpj().getText());
                pessoaJuridica.setInscricaoEstadual(this.viewFornecedor.getJtfInscricaoEstadual().getText());
                pessoaJuridica.setDataFundacao(this.viewFornecedor.getJtfDataFundacao().getText());
                fornecedor.setPessoaJuridicaIdPessoaJuridica(pessoaJuridica);
                new PessoaJuridicaController().updatePessoaJuridica(pessoaJuridica);

                cidade = listaCidades.get(this.viewFornecedor.getJcbCidade().getSelectedIndex() - 1);

                endereco.setLogradouro(this.viewFornecedor.getJtfEndereco().getText());
                endereco.setNumero(Integer.parseInt(this.viewFornecedor.getJtfNumero().getText()));
                endereco.setBairro(this.viewFornecedor.getJtfBairro().getText());
                endereco.setCep(this.viewFornecedor.getJtfCep().getText());
                endereco.setComplemento(this.viewFornecedor.getJtfComplemento().getText());
                endereco.setCidadeIdCidade(cidade);
                fornecedor.setEnderecoIdEndereco(endereco);
                new EnderecoController().updateEndereco(endereco);

                contato.setTelefone(this.viewFornecedor.getJtfTelefone().getText());
                contato.setCelular(this.viewFornecedor.getJtfCelular().getText());
                contato.setEmail(this.viewFornecedor.getJtfEmail().getText());
                fornecedor.setContatoIdContato(contato);
                new ContatoController().updateContato(contato);

                fornecedor.setContato(this.viewFornecedor.getJtfContato().getText());

                FornecedorDAO dao = new FornecedorDAO();
                try {
                    dao.salvar(fornecedor);
                    JOptionPane.showMessageDialog(null, Mensagem.fornecedorInserido);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroInserir);
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFornecedors();
            }
        } else {
            //alterando o registro
            if (validarSalvar()) {

                fornecedor.getPessoaJuridicaIdPessoaJuridica().setRazaoSocial(this.viewFornecedor.getJtfRazaoSocial().getText());
                fornecedor.getPessoaJuridicaIdPessoaJuridica().setCnpj(this.viewFornecedor.getJtfCnpj().getText());
                fornecedor.getPessoaJuridicaIdPessoaJuridica().setInscricaoEstadual(this.viewFornecedor.getJtfInscricaoEstadual().getText());
                fornecedor.getPessoaJuridicaIdPessoaJuridica().setDataFundacao(this.viewFornecedor.getJtfDataFundacao().getText());

                new PessoaJuridicaController().updatePessoaJuridica(fornecedor.getPessoaJuridicaIdPessoaJuridica());

                fornecedor.getEnderecoIdEndereco().setLogradouro(this.viewFornecedor.getJtfEndereco().getText());
                fornecedor.getEnderecoIdEndereco().setNumero(Integer.parseInt(this.viewFornecedor.getJtfNumero().getText()));
                fornecedor.getEnderecoIdEndereco().setBairro(this.viewFornecedor.getJtfBairro().getText());
                fornecedor.getEnderecoIdEndereco().setCep(this.viewFornecedor.getJtfCep().getText());
                fornecedor.getEnderecoIdEndereco().setComplemento(this.viewFornecedor.getJtfComplemento().getText());

                Cidade cidade = new Cidade();
                cidade = listaCidades.get(this.viewFornecedor.getJcbCidade().getSelectedIndex() - 1);
                fornecedor.getEnderecoIdEndereco().setCidadeIdCidade(cidade);
                new EnderecoController().updateEndereco(fornecedor.getEnderecoIdEndereco());
                fornecedor.getContatoIdContato().setTelefone(this.viewFornecedor.getJtfTelefone().getText());

                fornecedor.getContatoIdContato().setCelular(this.viewFornecedor.getJtfCelular().getText());
                fornecedor.getContatoIdContato().setEmail(this.viewFornecedor.getJtfEmail().getText());
                new ContatoController().updateContato(fornecedor.getContatoIdContato());
                fornecedor.setContato(this.viewFornecedor.getJtfContato().getText());
                FornecedorDAO dao = new FornecedorDAO();
                try {
                    dao.salvar(fornecedor);
                    JOptionPane.showMessageDialog(null, Mensagem.alteradoSucesso);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroAlterar);
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFornecedors();
            }
        }
    }

    public boolean validarSalvar() {

        if (!Valida.validaCnpj(this.viewFornecedor.getJtfCnpj().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cnpjInvalido);
            return false;
        }

        if (!Valida.validaIe(this.viewFornecedor.getJtfInscricaoEstadual().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.ieInvalido);
            return false;
        }

        if (!Valida.validaData(this.viewFornecedor.getJtfDataFundacao().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.dataInvalida);
            return false;
        }

        if (Valida.validaNome(this.viewFornecedor.getJtfRazaoSocial().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.nomeInvalido);
            return false;
        }

        if (!Valida.validaEmail(this.viewFornecedor.getJtfEmail().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.emailInvalido);
            return false;
        }

        if (Valida.validaEndereco(this.viewFornecedor.getJtfEndereco().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.enderecoInvalido);
            return false;
        }

        if (Valida.validaNumero(this.viewFornecedor.getJtfNumero().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.numeroInvalido);
            return false;
        }
        if (Valida.validaBairro(this.viewFornecedor.getJtfBairro().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.bairroInvalido);
            return false;
        }

        if (Valida.validaCidade(this.viewFornecedor.getJcbCidade().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.cidadeInvalida);
            return false;
        }

        if (Valida.validaCep(this.viewFornecedor.getJtfCep().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cepInvalido);
            return false;
        }

        if (Valida.validaEstado(this.viewFornecedor.getJcbEstado().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.estadoInvalido);
            return false;
        }
        if (Valida.validaLogin(this.viewFornecedor.getJtfContato().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.loginInvalido);
            return false;
        }

        return true;
    }

    public void alterarFornecedor() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFornecedor.getTabelaFornecedor().getModel();
        if (this.viewFornecedor.getTabelaFornecedor().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecaoErro);
        } else {
            fornecedor = listaFornecedors.get(this.viewFornecedor.getTabelaFornecedor().getSelectedRow());

            this.viewFornecedor.getJtfCnpj().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getCnpj());
            this.viewFornecedor.getJtfInscricaoEstadual().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
            this.viewFornecedor.getJtfRazaoSocial().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
            this.viewFornecedor.getJtfDataFundacao().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());

            this.viewFornecedor.getJtfEndereco().setText(fornecedor.getEnderecoIdEndereco().getLogradouro());
            this.viewFornecedor.getJtfNumero().setText(fornecedor.getEnderecoIdEndereco().getNumero() + "");
            this.viewFornecedor.getJtfBairro().setText(fornecedor.getEnderecoIdEndereco().getBairro());
            this.viewFornecedor.getJtfComplemento().setText(fornecedor.getEnderecoIdEndereco().getComplemento());
            this.viewFornecedor.getJtfCep().setText(fornecedor.getEnderecoIdEndereco().getCep());
            this.viewFornecedor.getJcbCidade().setSelectedItem(fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
            this.viewFornecedor.getJcbEstado().setSelectedItem(fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());
            this.viewFornecedor.getJtfTelefone().setText(fornecedor.getContatoIdContato().getTelefone());
            this.viewFornecedor.getJtfCelular().setText(fornecedor.getContatoIdContato().getCelular());
            this.viewFornecedor.getJtfEmail().setText(fornecedor.getContatoIdContato().getEmail());
            this.viewFornecedor.getJtfContato().setText(fornecedor.getContato());

            this.alterar = true;
            acaoBotaoAlterar();
        }
    }

    public void acaoBotaoAlterar() {
        this.viewFornecedor.getJbtNovo().setEnabled(false);
        this.viewFornecedor.getJbtAlterar().setEnabled(false);
        this.viewFornecedor.getJbtExcluir().setEnabled(false);
        this.viewFornecedor.getJbtSair().setEnabled(false);
        this.viewFornecedor.getJtfPesquisaRazaoSocial().setEditable(false);
        this.viewFornecedor.getJtfPesquisaCNPJ().setEditable(false);
        this.viewFornecedor.getJbtSalvar().setEnabled(true);
        this.viewFornecedor.getJbtCancelar().setEnabled(true);
        liberarCampos();

        this.viewFornecedor.getJtfCnpj().setEditable(false);
        this.viewFornecedor.getJtfInscricaoEstadual().setEditable(false);
        this.viewFornecedor.getJtfRazaoSocial().setEditable(false);
        this.viewFornecedor.getJtfDataFundacao().setEditable(false);
        this.viewFornecedor.getJtfEndereco().grabFocus();

    }

}
