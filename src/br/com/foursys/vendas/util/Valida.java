/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.foursys.vendas.view.FuncionarioPrincipal;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class Valida {
    public static boolean validaRg(String rg){
        if (rg.equals("00.000.000")) {
            JOptionPane.showMessageDialog(null, "RG INVALIDO");
            return false;
        }
        return true;
    }
    
    public static boolean validaCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        
        
       
        try {
            cpfValidator.assertValid(cpf.replace(".", "").replace("-", ""));
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "CPF INVALIDO");
            return false;
        }
        
        
    }
    
}
