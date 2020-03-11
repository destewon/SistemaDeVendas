package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.dao.ClienteDAO;
import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.Estado;
import br.com.foursys.vendas.model.PessoaFisica;
import br.com.foursys.vendas.model.PessoaJuridica;
import br.com.foursys.vendas.view.ClientePrincipal;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author dmunhoz
 */
public class ClienteController {

    private ClientePrincipal viewCliente;
    private Cliente cliente = new Cliente();
    private List<Cliente> listaClientes;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private boolean alterar;

    public ClienteController(ClientePrincipal viewCliente) {
        this.viewCliente = viewCliente;
    }
    
    public void excluirCliente() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTabelaCliente().getModel();
        if (this.viewCliente.getTabelaCliente().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente");
        } else {
            cliente = listaClientes.get(this.viewCliente.getTabelaCliente().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma em excluir este registro?", "Atenção",
                    JOptionPane.YES_OPTION,
                    JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                ClienteDAO dao = new ClienteDAO();
                try {
                    dao.excluir(cliente);
                    new ContatoController().excluirContato(cliente.getContatoIdContato());
                    new EnderecoController().excluirEndereco(cliente.getEnderecoIdEndereco());
                    if (cliente.getTipoPessoa().equals("F")) {
                        new PessoaFisicaController().excluirPessoaFisica(cliente.getPessoaFisicaIdPessoaFisica());
                    }else{
                        new PessoaJuridicaController().excluirPessoaJuridica(cliente.getPessoaJuridicaIdPessoaJuridica());
                    }
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
                    listarClientes();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente!");
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void listarClientes() {
        try {
            ClienteDAO dao = new ClienteDAO();
            listaClientes = dao.buscarTodos();
            carregarTabela();
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTabelaCliente().getModel();
        modelo.setRowCount(0);
        for (Cliente cliente : listaClientes) {
            if (cliente.getTipoPessoa().equals("F")) {
                modelo.addRow(new String[]{cliente.getPessoaFisicaIdPessoaFisica().getNome(), cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome(), cliente.getContatoIdContato().getCelular(), cliente.getContatoIdContato().getEmail()});
            } else {
                modelo.addRow(new String[]{cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(), cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome(), cliente.getContatoIdContato().getCelular(), cliente.getContatoIdContato().getEmail()});
            }
        }
    }

    public void bloqueioInicial() {
        this.viewCliente.getJbtNovo().setEnabled(true);
        this.viewCliente.getJbtAlterar().setEnabled(true);
        this.viewCliente.getJbtExcluir().setEnabled(true);
        this.viewCliente.getJbtSair().setEnabled(true);
        this.viewCliente.getJbtSalvar().setEnabled(false);
        this.viewCliente.getJbtCancelar().setEnabled(false);
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewCliente.getJtfPesquisarNome().setEditable(true);
        this.viewCliente.getJtfPesquisarNome().grabFocus();
        this.viewCliente.getJtfCpf().setEditable(false);
        this.viewCliente.getJtfRg().setEditable(false);
        this.viewCliente.getJtfDataNascimento().setEditable(false);
        this.viewCliente.getJtfNome().setEditable(false);
        this.viewCliente.getJtfEndereco().setEditable(false);
        this.viewCliente.getJtfNumero().setEditable(false);
        this.viewCliente.getJtfBairro().setEditable(false);
        this.viewCliente.getJcbCidade().setEnabled(false);
        this.viewCliente.getJcbEstado().setEnabled(false);
        this.viewCliente.getJtfTelefone().setEditable(false);
        this.viewCliente.getJtfDataNascimento().setEditable(false);
        this.viewCliente.getJrbFisico().setEnabled(false);
        this.viewCliente.getJrbJuridico().setEnabled(false);
    }

    public void limparCampos() {
        this.viewCliente.getJtfCpf().setText(null);
        this.viewCliente.getJtfCpf().setValue(null);
        this.viewCliente.getJtfRg().setText(null);
        this.viewCliente.getJtfRg().setValue(null);
        this.viewCliente.getJtfDataNascimento().setText(null);
        this.viewCliente.getJtfDataNascimento().setValue(null);
        this.viewCliente.getJtfNome().setText(null);
        this.viewCliente.getJtfEndereco().setText(null);
        this.viewCliente.getJtfNumero().setText(null);
        this.viewCliente.getJtfBairro().setText(null);
        this.viewCliente.getJcbCidade().setSelectedIndex(0);
        this.viewCliente.getJcbEstado().setSelectedIndex(0);
        this.viewCliente.getJtfTelefone().setText(null);
        this.viewCliente.getJtfTelefone().setValue(null);
        this.viewCliente.getButtonGroup1().clearSelection();
        
        this.viewCliente.getJtfCelular().setText(null);
        this.viewCliente.getJtfCelular().setValue(null);
        this.viewCliente.getJtfEmail().setText(null);
        this.viewCliente.getJtfComplemento().setText(null);
        this.viewCliente.getJtfCep().setText(null);
        this.viewCliente.getJtfCep().setValue(null);
    }

    public void acaoBotaoNovo() {
        this.viewCliente.getJbtNovo().setEnabled(false);
        this.viewCliente.getJbtAlterar().setEnabled(false);
        this.viewCliente.getJbtExcluir().setEnabled(false);
        this.viewCliente.getJbtSair().setEnabled(false);
        this.viewCliente.getJbtSalvar().setEnabled(true);
        this.viewCliente.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.alterar = false;
    }

    public void liberarCampos() {
        this.viewCliente.getJtfPesquisarNome().setEditable(false);
        this.viewCliente.getJtfCpf().grabFocus();
        this.viewCliente.getJtfCpf().setEditable(true);
        this.viewCliente.getJtfRg().setEditable(true);
        this.viewCliente.getJtfNome().setEditable(true);
        this.viewCliente.getJtfEndereco().setEditable(true);
        this.viewCliente.getJtfNumero().setEditable(true);
        this.viewCliente.getJtfBairro().setEditable(true);
        this.viewCliente.getJcbCidade().setEnabled(true);
        this.viewCliente.getJcbEstado().setEnabled(true);
        this.viewCliente.getJtfTelefone().setEditable(true);
        this.viewCliente.getJtfDataNascimento().setEditable(true);
        this.viewCliente.getJrbFisico().setEnabled(true);
        this.viewCliente.getJrbJuridico().setEnabled(true);
    }

    public void acaoBotaoCancelar() {
        this.viewCliente.getJbtNovo().setEnabled(true);
        this.viewCliente.getJbtAlterar().setEnabled(true);
        this.viewCliente.getJbtExcluir().setEnabled(true);
        this.viewCliente.getJbtSair().setEnabled(true);
        this.viewCliente.getJbtSalvar().setEnabled(false);
        this.viewCliente.getJbtCancelar().setEnabled(false);
        limparCampos();
        bloquearCampos();
    }

    public void carregarComboCidade() {
        CidadeController controller = new CidadeController();
        try {
            listaCidades = controller.buscarCidades();
            this.viewCliente.getJcbCidade().removeAllItems();
            this.viewCliente.getJcbCidade().addItem("- Selecione Cidade -");
            for (Cidade cidade : listaCidades) {
                this.viewCliente.getJcbCidade().addItem(cidade.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarComboEstado() {
        EstadoController controller = new EstadoController();
        try {
            listaEstados = controller.buscarEstados();
            this.viewCliente.getJcbEstado().removeAllItems();
            this.viewCliente.getJcbEstado().addItem("- Selecione Estado -");
            for (Estado estado : listaEstados) {
                this.viewCliente.getJcbEstado().addItem(estado.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mascaraCnpj() {
        try {
            this.viewCliente.getJtfCpf().setValue(null);
            MaskFormatter cnpj = new MaskFormatter("##.###.###/####-##");
            this.viewCliente.getJtfCpf().setFormatterFactory(
                    new DefaultFormatterFactory(cnpj));
            this.viewCliente.getJlbCpf().setText("CNPJ:");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void mascaraCpf() {
        try {
            this.viewCliente.getJtfCpf().setValue(null);
            MaskFormatter cnpj = new MaskFormatter("###.###.###-##");
            this.viewCliente.getJtfCpf().setFormatterFactory(
                    new DefaultFormatterFactory(cnpj));
            this.viewCliente.getJlbCpf().setText("CPF:");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void mascaraInscricaoEstadual() {

        try {
            this.viewCliente.getJtfRg().setValue(null);
            MaskFormatter cnpj = new MaskFormatter("###.###.###.###");
            this.viewCliente.getJtfRg().setFormatterFactory(
                    new DefaultFormatterFactory(cnpj));
            this.viewCliente.getJlbRg().setText("IE:");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void mascaraRg() {

        try {
            this.viewCliente.getJtfRg().setValue(null);
            MaskFormatter cnpj = new MaskFormatter("##.###.###");
            this.viewCliente.getJtfRg().setFormatterFactory(
                    new DefaultFormatterFactory(cnpj));
            this.viewCliente.getJlbRg().setText("RG:");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void salvarCliente() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Cliente cliente = new Cliente();
                PessoaFisica pessoaFisica = new PessoaFisica();
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                Endereco endereco = new Endereco();
                Cidade cidade = new Cidade();
                Contato contato = new Contato();

                if (this.viewCliente.getJrbFisico().isSelected()) {
                    pessoaFisica.setNome(this.viewCliente.getJtfNome().getText());
                    pessoaFisica.setCpf(this.viewCliente.getJtfCpf().getText());
                    pessoaFisica.setRg(this.viewCliente.getJtfRg().getText());
                    pessoaFisica.setDataNascimento(this.viewCliente.getJtfDataNascimento().getText());
                    cliente.setTipoPessoa("F");
                    cliente.setPessoaFisicaIdPessoaFisica(pessoaFisica);
                    new PessoaFisicaController().updatePessoaFisica(pessoaFisica);
                } else {
                    pessoaJuridica.setRazaoSocial(this.viewCliente.getJtfNome().getText());
                    pessoaJuridica.setCnpj(this.viewCliente.getJtfCpf().getText());
                    pessoaJuridica.setInscricaoEstadual(this.viewCliente.getJtfRg().getText());
                    pessoaJuridica.setDataFundacao(this.viewCliente.getJtfDataNascimento().getText());
                    cliente.setTipoPessoa("J");
                    cliente.setPessoaJuridicaIdPessoaJuridica(pessoaJuridica);
                    new PessoaJuridicaController().updatePessoaJuridica(pessoaJuridica);
                }
                endereco.setLogradouro(this.viewCliente.getJtfEndereco().getText());
                endereco.setNumero(Integer.parseInt(this.viewCliente.getJtfNumero().getText()));
                endereco.setBairro(this.viewCliente.getJtfBairro().getText());
                endereco.setCep(this.viewCliente.getJtfCep().getText());

                cidade = listaCidades.get(this.viewCliente.getJcbCidade().getSelectedIndex() - 1);
                endereco.setCidadeIdCidade(cidade);
                cliente.setEnderecoIdEndereco(endereco);
                new EnderecoController().updateEndereco(endereco);
                contato.setTelefone(this.viewCliente.getJtfTelefone().getText());
                contato.setEmail(this.viewCliente.getJtfEmail().getText());
                contato.setCelular(this.viewCliente.getJtfCelular().getText());
                cliente.setContatoIdContato(contato);
                new ContatoController().updateContato(contato);
                ClienteDAO dao = new ClienteDAO();
                try {
                    dao.salvar(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente.");
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarClientes();
            }
        } else {
            //alterando o registro
            if (validarSalvar()) {
                if (this.viewCliente.getJrbFisico().isSelected()) {
                    cliente.getPessoaFisicaIdPessoaFisica().setNome(this.viewCliente.getJtfNome().getText());
                    cliente.getPessoaFisicaIdPessoaFisica().setCpf(this.viewCliente.getJtfCpf().getText());
                    cliente.getPessoaFisicaIdPessoaFisica().setRg(this.viewCliente.getJtfRg().getText());
                    cliente.getPessoaFisicaIdPessoaFisica().setDataNascimento(this.viewCliente.getJtfDataNascimento().getText());
                    cliente.setTipoPessoa("F");
                    new PessoaFisicaController().updatePessoaFisica(cliente.getPessoaFisicaIdPessoaFisica());
                } else {
                    cliente.getPessoaJuridicaIdPessoaJuridica().setRazaoSocial(this.viewCliente.getJtfNome().getText());
                    cliente.getPessoaJuridicaIdPessoaJuridica().setCnpj(this.viewCliente.getJtfCpf().getText());
                    cliente.getPessoaJuridicaIdPessoaJuridica().setInscricaoEstadual(this.viewCliente.getJtfRg().getText());
                    cliente.getPessoaJuridicaIdPessoaJuridica().setDataFundacao(this.viewCliente.getJtfDataNascimento().getText());
                    cliente.setTipoPessoa("J");
                    new PessoaJuridicaController().updatePessoaJuridica(cliente.getPessoaJuridicaIdPessoaJuridica());
                }
                cliente.getEnderecoIdEndereco().setLogradouro(this.viewCliente.getJtfEndereco().getText());
                cliente.getEnderecoIdEndereco().setNumero(Integer.parseInt(this.viewCliente.getJtfNumero().getText()));
                cliente.getEnderecoIdEndereco().setBairro(this.viewCliente.getJtfBairro().getText());
                cliente.getEnderecoIdEndereco().setCep(this.viewCliente.getJtfCep().getText());
                cliente.getEnderecoIdEndereco().setComplemento(this.viewCliente.getJtfComplemento().getText());

                Cidade cidade = new Cidade();
                cidade = listaCidades.get(this.viewCliente.getJcbCidade().getSelectedIndex() - 1);
                cliente.getEnderecoIdEndereco().setCidadeIdCidade(cidade);
                new EnderecoController().updateEndereco(cliente.getEnderecoIdEndereco());
                cliente.getContatoIdContato().setTelefone(this.viewCliente.getJtfTelefone().getText());
                cliente.getContatoIdContato().setEmail(this.viewCliente.getJtfEmail().getText());
                cliente.getContatoIdContato().setCelular(this.viewCliente.getJtfCelular().getText());
                new ContatoController().updateContato(cliente.getContatoIdContato());
                ClienteDAO dao = new ClienteDAO();
                try {
                    dao.salvar(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente.");
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarClientes();
            }
        }
    }

    public boolean validarSalvar() {
        if (!this.viewCliente.getJrbFisico().isSelected() && !this.viewCliente.getJrbJuridico().isSelected()) {
            JOptionPane.showMessageDialog(null, "Informe o tipo de pessoa, campo obrigatório.");
            return false;
        }
        if (this.viewCliente.getJrbFisico().isSelected()) {
            //valida pessoa fisica
            if (this.viewCliente.getJtfCpf().equals("   .   .   -  ")) {
                JOptionPane.showMessageDialog(null, "Informe o CPF, campo obrigatório.");
                return false;
            }
            if (this.viewCliente.getJtfRg().getText().equals("  .   .   ")) {
            JOptionPane.showMessageDialog(null, "Informe o RG, campo obrigatório.");
            return false;
        }
        } else {
            //valida pessoa juridica
            if (this.viewCliente.getJtfCpf().equals("  .   .   /    -  ")) {
                JOptionPane.showMessageDialog(null, "Informe o CNPJ, campo obrigatório.");
                return false;
            }
            if (this.viewCliente.getJtfRg().getText().equals("   .   .   ")) {
            JOptionPane.showMessageDialog(null, "Informe o IE, campo obrigatório.");
            return false;
        }
        }
         if (this.viewCliente.getJtfNome().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o NOME, campo obrigatório.");
            return false;
        }
        if (this.viewCliente.getJtfDataNascimento().getText().equals("  /  /    ")) {
            JOptionPane.showMessageDialog(null, "Informe o DATA DE NASCIMENTO, campo obrigatório.");
            return false;
        }

        if (this.viewCliente.getJtfEndereco().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o ENDEREÇO, campo obrigatório.");
            return false;
        }

        if (this.viewCliente.getJtfNumero().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o NUMERO, campo obrigatório.");
            return false;
        }
        if (this.viewCliente.getJtfBairro().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o BAIRRO, campo obrigatório.");
            return false;
        }

        if (this.viewCliente.getJtfComplemento().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o COMPLEMENTO, campo obrigatório.");
            return false;
        }

        if (this.viewCliente.getJcbCidade().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o CIDADE, campo obrigatório.");
            return false;
        }

        if (this.viewCliente.getJtfCep().getText().equals("  .   -   ")) {
            JOptionPane.showMessageDialog(null, "Informe o CEP, campo obrigatório.");
            return false;
        }

        if (this.viewCliente.getJcbEstado().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o UF, campo obrigatório.");
            return false;
        }
        return true;
    }

    public void alterarCliente() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTabelaCliente().getModel();
        if (this.viewCliente.getTabelaCliente().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente");
        } else {
            cliente = listaClientes.get(this.viewCliente.getTabelaCliente().getSelectedRow());
            if (cliente.getTipoPessoa().equals("F")) {
                this.viewCliente.getJrbFisico().setSelected(true);
                this.viewCliente.getJtfCpf().setText(cliente.getPessoaFisicaIdPessoaFisica().getCpf());
                this.viewCliente.getJtfRg().setText(cliente.getPessoaFisicaIdPessoaFisica().getRg());
                this.viewCliente.getJtfNome().setText(cliente.getPessoaFisicaIdPessoaFisica().getNome());
                this.viewCliente.getJtfDataNascimento().setText(cliente.getPessoaFisicaIdPessoaFisica().getDataNascimento());

            } else {
                this.viewCliente.getJrbJuridico().setSelected(true);
                this.viewCliente.getJtfCpf().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getCnpj());
                this.viewCliente.getJtfRg().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
                this.viewCliente.getJtfNome().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
                this.viewCliente.getJtfDataNascimento().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());

            }
            this.viewCliente.getJtfEndereco().setText(cliente.getEnderecoIdEndereco().getLogradouro());
            this.viewCliente.getJtfNumero().setText(cliente.getEnderecoIdEndereco().getNumero() + "");
            this.viewCliente.getJtfBairro().setText(cliente.getEnderecoIdEndereco().getBairro());
            this.viewCliente.getJtfCep().setText(cliente.getEnderecoIdEndereco().getCep());
            this.viewCliente.getJcbCidade().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
            this.viewCliente.getJcbEstado().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());
            this.viewCliente.getJtfTelefone().setText(cliente.getContatoIdContato().getTelefone());
            this.viewCliente.getJtfCelular().setText(cliente.getContatoIdContato().getCelular());
            this.viewCliente.getJtfEmail().setText(cliente.getContatoIdContato().getEmail());
            this.alterar = true;
            acaoBotaoAlterar();
        }
    }

    public void acaoBotaoAlterar() {
        this.viewCliente.getJbtNovo().setEnabled(false);
        this.viewCliente.getJbtAlterar().setEnabled(false);
        this.viewCliente.getJbtExcluir().setEnabled(false);
        this.viewCliente.getJbtSair().setEnabled(false);
        this.viewCliente.getJbtSalvar().setEnabled(true);
        this.viewCliente.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.viewCliente.getJrbFisico().setEnabled(false);
        this.viewCliente.getJrbJuridico().setEnabled(false);
        this.viewCliente.getJtfCpf().setEditable(false);
        this.viewCliente.getJtfRg().setEditable(false);
        this.viewCliente.getJtfNome().setEditable(false);
        this.viewCliente.getJtfDataNascimento().setEditable(false);
        this.viewCliente.getJtfEndereco().grabFocus();
    }

}
