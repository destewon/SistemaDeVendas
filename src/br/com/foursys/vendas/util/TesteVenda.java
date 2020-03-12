/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.VendaDAO;
import br.com.foursys.vendas.dao.PessoaFisicaDAO;
import br.com.foursys.vendas.model.Venda;
import br.com.foursys.vendas.model.Funcionario;
import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.model.PessoaFisica;
import br.com.foursys.vendas.model.PessoaJuridica;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteVenda {

    public void salvarVenda() {

        VendaDAO vendaDAO = new VendaDAO();
        Venda venda = new Venda();

        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(1);

        cliente.setIdCliente(8);

        //venda.setData("01/01/2000");
        venda.setValorTotal(500);
        //venda.setValorPago(600);
        //venda.setValorTroco(venda.getValorPago()-venda.getValorTotal());
        venda.setClienteIdCliente(cliente);
        venda.setFuncionarioIdFuncionario(funcionario);

        vendaDAO.salvar(venda);
        System.out.println("Venda salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        VendaDAO dao = new VendaDAO();
        Venda venda = dao.buscarPorCodigo(2);

        if (venda.getClienteIdCliente().getTipoPessoa().trim().equals("f")) {
            System.out.println("data:" + venda.getData());
            System.out.println("Valor Total:" + venda.getValorTotal());
            System.out.println("Valor Pago:" + venda.getValorPago());
            System.out.println("Troco:" + venda.getValorTroco());
            System.out.println("Funcionario:" + venda.getFuncionarioIdFuncionario().getPessoaFisicaIdPessoaFisica().getNome());
            System.out.println("Cliente:" + venda.getClienteIdCliente().getPessoaFisicaIdPessoaFisica().getNome());

        } else {
            System.out.println("data:" + venda.getData());
            System.out.println("Valor Total:" + venda.getValorTotal());
            System.out.println("Valor Pago:" + venda.getValorPago());
            System.out.println("Troco:" + venda.getValorTroco());
            System.out.println("Funcionario:" + venda.getFuncionarioIdFuncionario().getPessoaFisicaIdPessoaFisica().getNome());
            System.out.println("Cliente:" + venda.getClienteIdCliente().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        }

        System.exit(0);

    }

    public void buscarTodos() throws Exception {
        VendaDAO dao = new VendaDAO();
        ArrayList<Venda> lista = dao.buscarTodos();

        for (Venda venda1 : lista) {
           if (venda1.getClienteIdCliente().getTipoPessoa().trim().equals("f")) {
            System.out.println("data:" + venda1.getData());
            System.out.println("Valor Total:" + venda1.getValorTotal());
            System.out.println("Valor Pago:" + venda1.getValorPago());
            System.out.println("Troco:" + venda1.getValorTroco());
            System.out.println("Funcionario:" + venda1.getFuncionarioIdFuncionario().getPessoaFisicaIdPessoaFisica().getNome());
            System.out.println("Cliente:" + venda1.getClienteIdCliente().getPessoaFisicaIdPessoaFisica().getNome());

        } else {
            System.out.println("data:" + venda1.getData());
            System.out.println("Valor Total:" + venda1.getValorTotal());
            System.out.println("Valor Pago:" + venda1.getValorPago());
            System.out.println("Troco:" + venda1.getValorTroco());
            System.out.println("Funcionario:" + venda1.getFuncionarioIdFuncionario().getPessoaFisicaIdPessoaFisica().getNome());
            System.out.println("Cliente:" + venda1.getClienteIdCliente().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        }
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteVenda().buscarTodos();
    }

}
