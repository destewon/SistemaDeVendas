/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.foursys.vendas.dao.EstoqueDAO;
import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.model.Estoque;
import br.com.foursys.vendas.model.Funcionario;
import br.com.foursys.vendas.model.Produto;
import java.util.ArrayList;

/**
 *
 * @author amendes
 */
public class TesteEstoque {

    public void salvarEstoque() {

        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Estoque estoque = new Estoque();

        Produto produto = new Produto();

        produto.setIdProduto(3);

        estoque.setQuantidadeEstoque(12);
        estoque.setQuantidadeMinima(3);

        estoque.setProdutoIdProduto(produto);

        estoqueDAO.salvar(estoque);
        System.out.println("Estoque salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        EstoqueDAO dao = new EstoqueDAO();
        Estoque estoque = dao.buscarPorCodigo(1);

        System.out.println("Produto:" + estoque.getProdutoIdProduto().getDescricao());
        System.out.println("Quantidade em estoque:" + estoque.getQuantidadeEstoque());
        System.out.println("Quantidade minima em estoque:" + estoque.getQuantidadeMinima());

        System.exit(0);

    }

    public void buscarTodos() throws Exception {
        EstoqueDAO dao = new EstoqueDAO();
        ArrayList<Estoque> lista = dao.buscarTodos();

        for (Estoque estoque1 : lista) {
            System.out.println("Produto:" + estoque1.getProdutoIdProduto().getDescricao());
            System.out.println("Quantidade em estoque:" + estoque1.getQuantidadeEstoque());
            System.out.println("Quantidade minima em estoque:" + estoque1.getQuantidadeMinima());
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteEstoque().buscarTodos();
    }

}
