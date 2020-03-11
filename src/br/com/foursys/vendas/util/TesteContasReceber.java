package br.com.foursys.vendas.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.foursys.vendas.dao.ContasReceberDAO;
import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.model.ContasReceber;
import br.com.foursys.vendas.model.Funcionario;
import br.com.foursys.vendas.model.Produto;
import br.com.foursys.vendas.model.Venda;
import java.util.ArrayList;

/**
 *
 * @author amendes
 */
public class TesteContasReceber {

    public void salvarContasReceber() {

        ContasReceberDAO contasReceberDAO = new ContasReceberDAO();
        ContasReceber contasReceber = new ContasReceber();

        Venda venda = new Venda();

        venda.setIdVenda(2);

        contasReceber.setDataVencimento("05/08/2020");

        contasReceber.setPagamento("Pago");
        contasReceber.setVencida("");

        contasReceber.setVendaIdVenda(venda);

        contasReceberDAO.salvar(contasReceber);
        System.out.println("ContasReceber salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        ContasReceberDAO dao = new ContasReceberDAO();
        ContasReceber contasReceber = dao.buscarPorCodigo(1);

        System.out.println("Data de Vencimento:" + contasReceber.getDataVencimento());

        System.out.println("Pagamento:" + contasReceber.getPagamento());
        System.out.println("Conta Vencida:" + contasReceber.getVencida());

        System.exit(0);

    }

    public void buscarTodos() throws Exception {
        ContasReceberDAO dao = new ContasReceberDAO();
        ArrayList<ContasReceber> lista = dao.buscarTodos();

        for (ContasReceber contasReceber1 : lista) {

            System.out.println("Data de Vencimento:" + contasReceber1.getDataVencimento());

            System.out.println("Pagamento:" + contasReceber1.getPagamento());
            System.out.println("Conta Vencida:" + contasReceber1.getVencida());
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteContasReceber().buscarTodos();
    }

}
