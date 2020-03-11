/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.EnderecoDAO;
import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Endereco;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteEndereco {

    public void salvarEndereco() {

        EnderecoDAO dao = new EnderecoDAO();
        Endereco endereco = new Endereco();
        Cidade cidade = new Cidade();
        cidade.setIdCidade(2);

        endereco.setLogradouro("Rua Professor Queiros Filho");
        endereco.setNumero(392);
        endereco.setComplemento("ap 72");
        endereco.setBairro("Jd Silveira");
        endereco.setCep("06434-080");
        endereco.setCidadeIdCidade(cidade);

        dao.salvar(endereco);
        System.out.println("Endereco salvo com sucesso");
        System.exit(0);
    }

    public void buscarCodigo() throws Exception {
        EnderecoDAO dao = new EnderecoDAO();
        Endereco endereco = dao.buscarPorCodigo(1);
        System.out.println("Logradouro:" + endereco.getLogradouro());
        System.out.println("Numero:" + endereco.getNumero());
        System.out.println("Complemento:" + endereco.getComplemento());
        System.out.println("Bairro:" + endereco.getBairro());
        System.out.println("Cep:" + endereco.getCep());
        System.out.println("Cidade:" + endereco.getCidadeIdCidade().getNome());
        System.out.println("Estado:" + endereco.getCidadeIdCidade().getEstadoIdEstado().getNome());
        System.exit(0);
    }

    public void buscarTodos() throws Exception {
        EnderecoDAO dao = new EnderecoDAO();
        ArrayList<Endereco> lista = dao.buscarTodos();

        for (Endereco endereco1 : lista) {
            System.out.println("Logradouro:" + endereco1.getLogradouro());
            System.out.println("Numero:" + endereco1.getNumero());
            System.out.println("Complemento:" + endereco1.getComplemento());
            System.out.println("Bairro:" + endereco1.getBairro());
            System.out.println("Cep:" + endereco1.getCep());
            System.out.println("Cidade:" + endereco1.getCidadeIdCidade().getNome());
            System.out.println("Estado:" + endereco1.getCidadeIdCidade().getEstadoIdEstado().getNome());
            System.out.println("");

        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteEndereco().buscarTodos();
    }

}
