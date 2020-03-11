/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.ProdutoDAO;
import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.Fornecedor;
import br.com.foursys.vendas.model.Produto;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteProduto {

    public void salvarProduto() {

        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(2);

        produto.setDescricao("TECLADO");
        produto.setValorVenda(250);
        produto.setValorCusto(180);

        produto.setFornecedorIdFornecedor(fornecedor);

        dao.salvar(produto);
        System.out.println("Produto salvo com sucesso");

    }

    public void buscarCodigo() throws Exception {
        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = dao.buscarPorCodigo(1);
        System.out.println("Logradouro:" + produto.getDescricao());
        System.out.println("Numero:" + produto.getValorVenda());
        System.out.println("Complemento:" + produto.getValorCusto());
        System.out.println("Bairro:" + produto.getFornecedorIdFornecedor().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());

    }

    public void buscarTodos() throws Exception {
        ProdutoDAO dao = new ProdutoDAO();
        ArrayList<Produto> lista = dao.buscarTodos();

        for (Produto produto1 : lista) {
            System.out.println("Descrição:" + produto1.getDescricao());
            System.out.println("Valor de Venda:" + produto1.getValorVenda());
            System.out.println("Velor de Compra:" + produto1.getValorCusto());
            if (produto1.getFornecedorIdFornecedor().getTipoPessoa().trim().equals("f")) {
                System.out.println("Fornecedor:" + produto1.getFornecedorIdFornecedor().getPessoaFisicaIdPessoaFisica().getNome());
                System.out.println("");
            } else {
                System.out.println("Fornecedor:" + produto1.getFornecedorIdFornecedor().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
                System.out.println("");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new TesteProduto().buscarTodos();
    }

}
