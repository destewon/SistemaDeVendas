/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.ItemVendaDAO;
import br.com.foursys.vendas.dao.PessoaFisicaDAO;
import br.com.foursys.vendas.dao.ProdutoDAO;
import br.com.foursys.vendas.model.ItemVenda;
import br.com.foursys.vendas.model.ItemVenda;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.PessoaFisica;
import br.com.foursys.vendas.model.PessoaJuridica;
import br.com.foursys.vendas.model.Produto;
import br.com.foursys.vendas.model.Venda;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteItemVenda {

    public void salvarItemVenda() throws Exception {

        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
        ItemVenda itemVenda = new ItemVenda();
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();
        Venda venda = new Venda();
        Produto produto = new Produto();
        produto.setIdProduto(1);
        //PessoaJuridica pessoaJuridica= new PessoaJuridica();
        ProdutoDAO dao2 = new ProdutoDAO();
        Produto produto2 = dao2.buscarPorCodigo(3);

        venda.setIdVenda(2);

        //itemVenda.setItemVenda("569245698");
        itemVenda.setQuantidadeProduto(5);
        itemVenda.setDescontoProduto(300.0 * itemVenda.getQuantidadeProduto());
        itemVenda.setValorTotal(produto2.getValorVenda() * itemVenda.getQuantidadeProduto() - itemVenda.getDescontoProduto());

        itemVenda.setVendaIdVenda(venda);
        itemVenda.setProdutoIdProduto(produto);

        itemVendaDAO.salvar(itemVenda);
        System.out.println("ItemVenda salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        ItemVendaDAO dao = new ItemVendaDAO();
        ItemVenda itemVenda = dao.buscarPorCodigo(8);

        System.out.println("Quantidade:" + itemVenda.getQuantidadeProduto());
        System.out.println("Desconto:" + itemVenda.getDescontoProduto());
        System.out.println("Valor Total:" + itemVenda.getValorTotal());
        System.out.println("Venda:" + itemVenda.getVendaIdVenda().getIdVenda());
        System.out.println("Produto:" + itemVenda.getProdutoIdProduto().getDescricao());

        System.exit(0);

    }

    public void buscarTodos() throws Exception {
        ItemVendaDAO dao = new ItemVendaDAO();
        ArrayList<ItemVenda> lista = dao.buscarTodos();

        for (ItemVenda itemVenda1 : lista) {

            System.out.println("Quantidade:" + itemVenda1.getQuantidadeProduto());
            System.out.println("Desconto:" + itemVenda1.getDescontoProduto());
            System.out.println("Valor Total:" + itemVenda1.getValorTotal());
            System.out.println("Venda:" + itemVenda1.getVendaIdVenda().getIdVenda());
            System.out.println("Produto:" + itemVenda1.getProdutoIdProduto().getDescricao());
            System.out.println("");
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteItemVenda().buscarTodos();

    }

}
