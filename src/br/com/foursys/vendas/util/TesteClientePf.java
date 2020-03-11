/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.ClienteDAO;
import br.com.foursys.vendas.dao.PessoaFisicaDAO;
import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.PessoaFisica;
import br.com.foursys.vendas.model.PessoaJuridica;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteClientePf {

    public void salvarCliente() {

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();
        Endereco endereco = new Endereco();
        Contato contato = new Contato();
        contato.setIdContato(1);
        //PessoaJuridica pessoaJuridica= new PessoaJuridica();

        pessoaFisica.setNome("Matheus Poda");
        pessoaFisica.setRg("66.666.666-6");
        pessoaFisica.setCpf("666.666.666-6");
        pessoaFisica.setDataNascimento("23/10/2000");
        pessoaFisicaDAO.salvar(pessoaFisica);
        System.out.println("PessoaFisica salvo com sucesso");
        endereco.setIdEndereco(2);

        //cliente.setContato("569245698");
        cliente.setTipoPessoa("f");
        cliente.setEnderecoIdEndereco(endereco);
        cliente.setContatoIdContato(contato);
        cliente.setPessoaFisicaIdPessoaFisica(pessoaFisica);

        clienteDAO.salvar(cliente);
        System.out.println("Cliente salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = dao.buscarPorCodigo(8);
        if (cliente.getTipoPessoa().trim().equals("f")) {
            System.out.println("Logradouro:" + cliente.getEnderecoIdEndereco().getLogradouro());

            System.out.println("Numero:" + cliente.getEnderecoIdEndereco().getNumero());
            System.out.println("Complemento:" + cliente.getEnderecoIdEndereco().getComplemento());
            System.out.println("Bairro:" + cliente.getEnderecoIdEndereco().getBairro());
            System.out.println("cep:" + cliente.getEnderecoIdEndereco().getCep());

            System.out.println("Cidade:" + cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
            System.out.println("Estado:" + cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

            System.out.println("Telefone:" + cliente.getContatoIdContato().getTelefone());
            System.out.println("Celular:" + cliente.getContatoIdContato().getCelular());
            System.out.println("Email:" + cliente.getContatoIdContato().getEmail());

            System.out.println("Nome:" + cliente.getPessoaFisicaIdPessoaFisica().getNome());
            System.out.println("Rg:" + cliente.getPessoaFisicaIdPessoaFisica().getRg());
            System.out.println("Cpf:" + cliente.getPessoaFisicaIdPessoaFisica().getCpf());
            System.out.println("Data de nascimento:" + cliente.getPessoaFisicaIdPessoaFisica().getDataNascimento());
            System.out.println("");
            
        }
        System.exit(0);
    }

    public void buscarTodos() throws Exception {
        ClienteDAO dao = new ClienteDAO();
        ArrayList<Cliente> lista = dao.buscarTodos();

        for (Cliente cliente1 : lista) {
            if (cliente1.getTipoPessoa().trim().equals("f")) {

                System.out.println("Logradouro:" + cliente1.getEnderecoIdEndereco().getLogradouro());

                System.out.println("Numero:" + cliente1.getEnderecoIdEndereco().getNumero());
                System.out.println("Complemento:" + cliente1.getEnderecoIdEndereco().getComplemento());
                System.out.println("Bairro:" + cliente1.getEnderecoIdEndereco().getBairro());
                System.out.println("cep:" + cliente1.getEnderecoIdEndereco().getCep());

                System.out.println("Cidade:" + cliente1.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
                System.out.println("Estado:" + cliente1.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

                System.out.println("Telefone:" + cliente1.getContatoIdContato().getTelefone());
                System.out.println("Celular:" + cliente1.getContatoIdContato().getCelular());
                System.out.println("Email:" + cliente1.getContatoIdContato().getEmail());

                System.out.println("Nome:" + cliente1.getPessoaFisicaIdPessoaFisica().getNome());
                System.out.println("Rg:" + cliente1.getPessoaFisicaIdPessoaFisica().getRg());
                System.out.println("Cpf:" + cliente1.getPessoaFisicaIdPessoaFisica().getCpf());
                System.out.println("Data de nascimento:" + cliente1.getPessoaFisicaIdPessoaFisica().getDataNascimento());
                System.out.println("");
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteClientePf().salvarCliente();
    }

}
