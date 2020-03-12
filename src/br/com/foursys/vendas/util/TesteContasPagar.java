package br.com.foursys.vendas.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.foursys.vendas.dao.ContasPagarDAO;
import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.model.ContasPagar;
import br.com.foursys.vendas.model.Funcionario;
import br.com.foursys.vendas.model.Produto;
import java.util.ArrayList;

/**
 *
 * @author amendes
 */
public class TesteContasPagar {

    public void salvarContasPagar() {

        ContasPagarDAO contasPagarDAO = new ContasPagarDAO();
        ContasPagar contasPagar = new ContasPagar();

        Produto produto = new Produto();

        produto.setIdProduto(3);

        contasPagar.setDataVencimento("02/06/2020");
        contasPagar.setDataPagamento("02/08/2020");
        //contasPagar.setValorCompra(10000);
        //contasPagar.setQuantidadeProduto(5);
        contasPagar.setPagamento("");
        contasPagar.setVencida("");

        //contasPagar.setProdutoIdProduto(produto);

        contasPagarDAO.salvar(contasPagar);
        System.out.println("ContasPagar salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        ContasPagarDAO dao = new ContasPagarDAO();
        ContasPagar contasPagar = dao.buscarPorCodigo(1);

        System.out.println("Data de Pagamento:" + contasPagar.getDataPagamento());
        System.out.println("Data de Vencimento:" + contasPagar.getDataVencimento());
        //System.out.println("Valor da Compra:" + contasPagar.getValorCompra());

        //System.out.println("Descrição:" + contasPagar.getProdutoIdProduto().getDescricao());

        //System.out.println("Quantidade:" + contasPagar.getQuantidadeProduto());
        System.out.println("Pagamento:" + contasPagar.getPagamento());
        System.out.println("Conta Vencida:" + contasPagar.getVencida());
        System.out.println("");
        System.exit(0);

    }

    public void buscarTodos() throws Exception {
        ContasPagarDAO dao = new ContasPagarDAO();
        ArrayList<ContasPagar> lista = dao.buscarTodos();

        for (ContasPagar contasPagar1 : lista) {
            System.out.println("Data de Pagamento:" + contasPagar1.getDataPagamento());
            System.out.println("Data de Vencimento:" + contasPagar1.getDataVencimento());
            //System.out.println("Valor da Compra:" + contasPagar1.getValorCompra());

            //System.out.println("Descrição:" + contasPagar1.getProdutoIdProduto().getDescricao());

            //System.out.println("Quantidade:" + contasPagar1.getQuantidadeProduto());
            System.out.println("Pagamento:" + contasPagar1.getPagamento());
            System.out.println("Conta Vencida:" + contasPagar1.getVencida());
            System.out.println("");
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteContasPagar().buscarTodos();
    }

}
