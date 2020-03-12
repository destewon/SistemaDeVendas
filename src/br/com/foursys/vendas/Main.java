/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas;

import br.com.foursys.vendas.view.ClientePrincipal;
import br.com.foursys.vendas.view.FornecedorPrincipal;
import br.com.foursys.vendas.view.FuncionarioPrincipal;
import br.com.foursys.vendas.view.ProdutoPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author amendes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
    // handle exception
        } catch (ClassNotFoundException e) {
    // handle exception
        } catch (InstantiationException e) {
    // handle exception
        } catch (IllegalAccessException e) {
    // handle exception
        }
       //new FuncionarioPrincipal();
       // new ClientePrincipal();
       //new FornecedorPrincipal();
       new ProdutoPrincipal();
    }

}
