package br.com.foursys.vendas.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.foursys.vendas.dao.FuncionarioDAO;
import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Funcionario;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.Estado;
import br.com.foursys.vendas.util.Mensagem;
import br.com.foursys.vendas.model.PessoaFisica;
import br.com.foursys.vendas.model.PessoaJuridica;
import br.com.foursys.vendas.util.Valida;
import br.com.foursys.vendas.view.FuncionarioPrincipal;
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
public class FuncionarioController {

    private FuncionarioPrincipal viewFuncionario;
    private Funcionario funcionario = new Funcionario();
    private List<Funcionario> listaFuncionarios;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private boolean alterar;

    public FuncionarioController(FuncionarioPrincipal viewFuncionario) {
        this.viewFuncionario = viewFuncionario;
    }

   public FuncionarioController() {
        
    }
    public List<Funcionario> buscarTodos(String login){
        FuncionarioDAO dao = new FuncionarioDAO();
        List <Funcionario> listaFuncionario = null;
        
        try {
            listaFuncionario =dao.buscarTodos(login);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaFuncionario;
        
    }

    public void excluirFuncionario() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFuncionario.getTabelaFuncionario().getModel();
        if (this.viewFuncionario.getTabelaFuncionario().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecaoErro);
        } else {
            funcionario = listaFuncionarios.get(this.viewFuncionario.getTabelaFuncionario().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.confirmaExcluir, "Atenção",
                    JOptionPane.YES_OPTION,
                    JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                FuncionarioDAO dao = new FuncionarioDAO();
                try {
                    dao.excluir(funcionario);
                    new ContatoController().excluirContato(funcionario.getContatoIdContato());
                    new EnderecoController().excluirEndereco(funcionario.getEnderecoIdEndereco());

                    new PessoaFisicaController().excluirPessoaFisica(funcionario.getPessoaFisicaIdPessoaFisica());

                    JOptionPane.showMessageDialog(null, Mensagem.excluidoComSucesso);
                    listarFuncionarios();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroExclusao);
                    Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void listarFuncionarios() {
        try {
            FuncionarioDAO dao = new FuncionarioDAO();
            listaFuncionarios = dao.buscarTodos();
            carregarTabela();
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFuncionario.getTabelaFuncionario().getModel();
        modelo.setRowCount(0);
        for (Funcionario funcionario : listaFuncionarios) {

            modelo.addRow(new String[]{funcionario.getPessoaFisicaIdPessoaFisica().getNome(), funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getNome(), funcionario.getContatoIdContato().getTelefone(), funcionario.getContatoIdContato().getCelular()});

        }
    }

    public void bloqueioInicial() {
        this.viewFuncionario.getJbtNovo().setEnabled(true);
        this.viewFuncionario.getJbtAlterar().setEnabled(true);
        this.viewFuncionario.getJbtExcluir().setEnabled(true);
        this.viewFuncionario.getJbtSair().setEnabled(true);
        this.viewFuncionario.getJbtPesquisarFuncionario().setEnabled(true);
        this.viewFuncionario.getJbtSalvar().setEnabled(false);
        this.viewFuncionario.getJbtCancelar().setEnabled(false);
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewFuncionario.getJtfPesquisarNome().setEditable(true);
        this.viewFuncionario.getJtfPesquisarNome().grabFocus();
        this.viewFuncionario.getJtfCpf().setEditable(false);
        this.viewFuncionario.getJtfRg().setEditable(false);
        this.viewFuncionario.getJtfDataNascimento().setEditable(false);
        this.viewFuncionario.getJtfNome().setEditable(false);
        this.viewFuncionario.getJtfEndereco().setEditable(false);
        this.viewFuncionario.getJtfNumero().setEditable(false);
        this.viewFuncionario.getJtfBairro().setEditable(false);
        this.viewFuncionario.getJcbCidade().setEnabled(false);
        this.viewFuncionario.getJcbEstado().setEnabled(false);
        this.viewFuncionario.getJtfTelefone().setEditable(false);
        this.viewFuncionario.getJtfDataNascimento().setEditable(false);
        this.viewFuncionario.getJtfLogin().setEditable(false);
        this.viewFuncionario.getJtfSenha().setEditable(false);
        this.viewFuncionario.getJtfComplemento().setEditable(false);
        this.viewFuncionario.getJtfEmail().setEditable(false);

    }

    public void limparCampos() {
        this.viewFuncionario.getJtfCpf().setText(null);
        this.viewFuncionario.getJtfCpf().setValue(null);
        this.viewFuncionario.getJtfRg().setText(null);
        this.viewFuncionario.getJtfRg().setValue(null);
        this.viewFuncionario.getJtfDataNascimento().setText(null);
        this.viewFuncionario.getJtfDataNascimento().setValue(null);
        this.viewFuncionario.getJtfNome().setText(null);
        this.viewFuncionario.getJtfEndereco().setText(null);
        this.viewFuncionario.getJtfNumero().setText(null);
        this.viewFuncionario.getJtfBairro().setText(null);
        this.viewFuncionario.getJcbCidade().setSelectedIndex(0);
        this.viewFuncionario.getJcbEstado().setSelectedIndex(0);
        this.viewFuncionario.getJtfTelefone().setText(null);
        this.viewFuncionario.getJtfTelefone().setValue(null);
        this.viewFuncionario.getJtfSenha().setText(null);
        this.viewFuncionario.getJtfLogin().setText(null);

        this.viewFuncionario.getJtfCelular().setText(null);
        this.viewFuncionario.getJtfCelular().setValue(null);

        this.viewFuncionario.getJtfComplemento().setText(null);
        this.viewFuncionario.getJtfCep().setText(null);
        this.viewFuncionario.getJtfCep().setValue(null);

        this.viewFuncionario.getJtfEmail().setText(null);
    }

    public void acaoBotaoNovo() {
        this.viewFuncionario.getJbtNovo().setEnabled(false);
        this.viewFuncionario.getJbtAlterar().setEnabled(false);
        this.viewFuncionario.getJbtExcluir().setEnabled(false);
        this.viewFuncionario.getJbtSair().setEnabled(false);
        this.viewFuncionario.getJbtSalvar().setEnabled(true);
        this.viewFuncionario.getJbtCancelar().setEnabled(true);
        this.viewFuncionario.getJbtPesquisarFuncionario().setEnabled(false);
        liberarCampos();
        this.alterar = false;
    }

    public void liberarCampos() {
        this.viewFuncionario.getJtfPesquisarNome().setEditable(false);
        this.viewFuncionario.getJtfCpf().grabFocus();
        this.viewFuncionario.getJtfCpf().setEditable(true);
        this.viewFuncionario.getJtfRg().setEditable(true);
        this.viewFuncionario.getJtfNome().setEditable(true);
        this.viewFuncionario.getJtfEndereco().setEditable(true);
        this.viewFuncionario.getJtfNumero().setEditable(true);
        this.viewFuncionario.getJtfBairro().setEditable(true);
        this.viewFuncionario.getJcbCidade().setEnabled(true);
        this.viewFuncionario.getJcbEstado().setEnabled(true);
        this.viewFuncionario.getJtfTelefone().setEditable(true);
        this.viewFuncionario.getJtfDataNascimento().setEditable(true);
        this.viewFuncionario.getJtfLogin().setEditable(true);
        this.viewFuncionario.getJtfSenha().setEditable(true);
        this.viewFuncionario.getJtfComplemento().setEditable(true);
        this.viewFuncionario.getJtfEmail().setEditable(true);
    }

    public void acaoBotaoCancelar() {
        this.viewFuncionario.getJbtNovo().setEnabled(true);
        this.viewFuncionario.getJbtAlterar().setEnabled(true);
        this.viewFuncionario.getJbtExcluir().setEnabled(true);
        this.viewFuncionario.getJbtSair().setEnabled(true);
        this.viewFuncionario.getJbtSalvar().setEnabled(false);
        this.viewFuncionario.getJbtCancelar().setEnabled(false);
        this.viewFuncionario.getJbtPesquisarFuncionario().setEnabled(true);
        limparCampos();
        bloquearCampos();
    }

    public void carregarComboCidade() {
        CidadeController controller = new CidadeController();
        try {
            listaCidades = controller.buscarCidades();
            this.viewFuncionario.getJcbCidade().removeAllItems();
            this.viewFuncionario.getJcbCidade().addItem("- Selecione Cidade -");
            for (Cidade cidade : listaCidades) {
                this.viewFuncionario.getJcbCidade().addItem(cidade.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarComboEstado() {
        EstadoController controller = new EstadoController();
        try {
            listaEstados = controller.buscarEstados();
            this.viewFuncionario.getJcbEstado().removeAllItems();
            this.viewFuncionario.getJcbEstado().addItem("- Selecione Estado -");
            for (Estado estado : listaEstados) {
                this.viewFuncionario.getJcbEstado().addItem(estado.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvarFuncionario() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Funcionario funcionario = new Funcionario();
                PessoaFisica pessoaFisica = new PessoaFisica();
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                Endereco endereco = new Endereco();
                Cidade cidade = new Cidade();
                Contato contato = new Contato();

                pessoaFisica.setNome(this.viewFuncionario.getJtfNome().getText());
                pessoaFisica.setCpf(this.viewFuncionario.getJtfCpf().getText());
                pessoaFisica.setRg(this.viewFuncionario.getJtfRg().getText());
                pessoaFisica.setDataNascimento(this.viewFuncionario.getJtfDataNascimento().getText());
                funcionario.setPessoaFisicaIdPessoaFisica(pessoaFisica);
                new PessoaFisicaController().updatePessoaFisica(pessoaFisica);

                cidade = listaCidades.get(this.viewFuncionario.getJcbCidade().getSelectedIndex() - 1);

                endereco.setLogradouro(this.viewFuncionario.getJtfEndereco().getText());
                endereco.setNumero(Integer.parseInt(this.viewFuncionario.getJtfNumero().getText().trim()));
                endereco.setBairro(this.viewFuncionario.getJtfBairro().getText());
                endereco.setCep(this.viewFuncionario.getJtfCep().getText());
                endereco.setComplemento(this.viewFuncionario.getJtfComplemento().getText());
                endereco.setCidadeIdCidade(cidade);
                funcionario.setEnderecoIdEndereco(endereco);
                new EnderecoController().updateEndereco(endereco);

                contato.setTelefone(this.viewFuncionario.getJtfTelefone().getText());
                contato.setCelular(this.viewFuncionario.getJtfCelular().getText());
                contato.setEmail(this.viewFuncionario.getJtfEmail().getText());
                funcionario.setContatoIdContato(contato);
                new ContatoController().updateContato(contato);

                funcionario.setSenha(new String(this.viewFuncionario.getJtfSenha().getPassword()));

                funcionario.setLogin(this.viewFuncionario.getJtfLogin().getText().trim());

                FuncionarioDAO dao = new FuncionarioDAO();
                try {
                    dao.salvar(funcionario);
                    JOptionPane.showMessageDialog(null, Mensagem.funcionarioInserido);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroInserir);
                    Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFuncionarios();
            }
        } else {
            //alterando o registro
            if (validarSalvar()) {

                funcionario.getPessoaFisicaIdPessoaFisica().setNome(this.viewFuncionario.getJtfNome().getText());
                funcionario.getPessoaFisicaIdPessoaFisica().setCpf(this.viewFuncionario.getJtfCpf().getText());
                funcionario.getPessoaFisicaIdPessoaFisica().setRg(this.viewFuncionario.getJtfRg().getText());
                funcionario.getPessoaFisicaIdPessoaFisica().setDataNascimento(this.viewFuncionario.getJtfDataNascimento().getText());

                new PessoaFisicaController().updatePessoaFisica(funcionario.getPessoaFisicaIdPessoaFisica());

                funcionario.getEnderecoIdEndereco().setLogradouro(this.viewFuncionario.getJtfEndereco().getText());
                funcionario.getEnderecoIdEndereco().setNumero(Integer.parseInt(this.viewFuncionario.getJtfNumero().getText().trim()));
                funcionario.getEnderecoIdEndereco().setBairro(this.viewFuncionario.getJtfBairro().getText());
                funcionario.getEnderecoIdEndereco().setCep(this.viewFuncionario.getJtfCep().getText());
                funcionario.getEnderecoIdEndereco().setComplemento(this.viewFuncionario.getJtfComplemento().getText());

                Cidade cidade = new Cidade();
                cidade = listaCidades.get(this.viewFuncionario.getJcbCidade().getSelectedIndex() - 1);
                funcionario.getEnderecoIdEndereco().setCidadeIdCidade(cidade);
                new EnderecoController().updateEndereco(funcionario.getEnderecoIdEndereco());
                funcionario.getContatoIdContato().setTelefone(this.viewFuncionario.getJtfTelefone().getText());

                funcionario.getContatoIdContato().setCelular(this.viewFuncionario.getJtfCelular().getText());
                funcionario.getContatoIdContato().setEmail(this.viewFuncionario.getJtfEmail().getText());
                new ContatoController().updateContato(funcionario.getContatoIdContato());
                funcionario.setLogin(this.viewFuncionario.getJtfLogin().getText().trim());
                funcionario.setSenha(new String(this.viewFuncionario.getJtfSenha().getPassword()));
                FuncionarioDAO dao = new FuncionarioDAO();
                try {
                    dao.salvar(funcionario);
                    JOptionPane.showMessageDialog(null, Mensagem.funcionarioAlterado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Mensagem.erroAlterar);
                    Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFuncionarios();
            }
        }
    }

    public boolean validarSalvar() {

        if (!Valida.validaCpf(this.viewFuncionario.getJtfCpf().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cpfInvalido);
            return false;
        }

        if (Valida.validaRg(this.viewFuncionario.getJtfRg().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.rgInvalido);
            return false;
        }

        if (!Valida.validaData(this.viewFuncionario.getJtfDataNascimento().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.dataInvalida);
            return false;
        }

        if (Valida.validaNome(this.viewFuncionario.getJtfNome().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.nomeInvalido);
            return false;
        }

        if (!Valida.validaEmail(this.viewFuncionario.getJtfEmail().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.emailInvalido);
            return false;
        }

        if (Valida.validaEndereco(this.viewFuncionario.getJtfEndereco().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.enderecoInvalido);
            return false;
        }

        if (Valida.validaNumero(this.viewFuncionario.getJtfNumero().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.numeroInvalido);
            return false;
        }
        if (Valida.validaBairro(this.viewFuncionario.getJtfBairro().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.bairroInvalido);
            return false;
        }

        

        if (Valida.validaCidade(this.viewFuncionario.getJcbCidade().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.cidadeInvalida);
            return false;
        }

        if (Valida.validaCep(this.viewFuncionario.getJtfCep().getText())) {
            JOptionPane.showMessageDialog(null,Mensagem.cepInvalido );
            return false;
        }

        if (Valida.validaEstado(this.viewFuncionario.getJcbEstado().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.estadoInvalido);
            return false;
        }
        if (Valida.validaLogin(this.viewFuncionario.getJtfLogin().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.loginInvalido);
            return false;
        }

        if (Valida.validaSenha(new String(this.viewFuncionario.getJtfSenha().getPassword()))) {
            JOptionPane.showMessageDialog(null, Mensagem.senhaInvalida);
            return false;
        }

        return true;
    }

    public void alterarFuncionario() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFuncionario.getTabelaFuncionario().getModel();
        if (this.viewFuncionario.getTabelaFuncionario().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecaoErro);
        } else {
            funcionario = listaFuncionarios.get(this.viewFuncionario.getTabelaFuncionario().getSelectedRow());

            this.viewFuncionario.getJtfCpf().setText(funcionario.getPessoaFisicaIdPessoaFisica().getCpf());
            this.viewFuncionario.getJtfRg().setText(funcionario.getPessoaFisicaIdPessoaFisica().getRg());
            this.viewFuncionario.getJtfNome().setText(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
            this.viewFuncionario.getJtfDataNascimento().setText(funcionario.getPessoaFisicaIdPessoaFisica().getDataNascimento());

            this.viewFuncionario.getJtfEndereco().setText(funcionario.getEnderecoIdEndereco().getLogradouro());
            this.viewFuncionario.getJtfNumero().setText(funcionario.getEnderecoIdEndereco().getNumero() + "");
            this.viewFuncionario.getJtfBairro().setText(funcionario.getEnderecoIdEndereco().getBairro());
            this.viewFuncionario.getJtfComplemento().setText(funcionario.getEnderecoIdEndereco().getComplemento());
            this.viewFuncionario.getJtfCep().setText(funcionario.getEnderecoIdEndereco().getCep());
            this.viewFuncionario.getJcbCidade().setSelectedItem(funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
            this.viewFuncionario.getJcbEstado().setSelectedItem(funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());
            this.viewFuncionario.getJtfTelefone().setText(funcionario.getContatoIdContato().getTelefone());
            this.viewFuncionario.getJtfCelular().setText(funcionario.getContatoIdContato().getCelular());
            this.viewFuncionario.getJtfEmail().setText(funcionario.getContatoIdContato().getEmail());
            this.viewFuncionario.getJtfLogin().setText(funcionario.getLogin());
            this.viewFuncionario.getJtfSenha().setText(funcionario.getSenha());

            this.alterar = true;
            acaoBotaoAlterar();
        }
    }

    public void acaoBotaoAlterar() {
        this.viewFuncionario.getJbtNovo().setEnabled(false);
        this.viewFuncionario.getJbtAlterar().setEnabled(false);
        this.viewFuncionario.getJbtExcluir().setEnabled(false);
        this.viewFuncionario.getJbtSair().setEnabled(false);
        this.viewFuncionario.getJbtPesquisarFuncionario().setEnabled(false);
        this.viewFuncionario.getJbtSalvar().setEnabled(true);
        this.viewFuncionario.getJbtCancelar().setEnabled(true);
        liberarCampos();

        this.viewFuncionario.getJtfCpf().setEditable(false);
        this.viewFuncionario.getJtfRg().setEditable(false);
        this.viewFuncionario.getJtfNome().setEditable(false);
        this.viewFuncionario.getJtfDataNascimento().setEditable(false);
        this.viewFuncionario.getJtfEndereco().grabFocus();
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.assertValid(null);
    }

}
