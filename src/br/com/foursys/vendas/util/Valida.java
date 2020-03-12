/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.ie.IESaoPauloValidator;
import br.com.foursys.vendas.view.FuncionarioPrincipal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class Valida {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean validaRg(String rg) {

        //JOptionPane.showMessageDialog(null, "RG INVALIDO");
        return rg.replace(".", "").trim().equals("") || rg.trim().replace(".", "").equals("00000000");

    }

    public static boolean validaCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();

        // se passou pra cá, é porque a data é válida
        try {
            cpfValidator.assertValid(cpf.replace(".", "").replace("-", ""));

            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "CPF INVALIDO");
            //System.err.println("invalid");
            return false;

        }

    }
    
    public static boolean validaCnpj(String cnpj) {
        CNPJValidator cnpjValidator = new CNPJValidator();

        // se passou pra cá, é porque a data é válida
        try {
            //cpfValidator.assertValid(cnpj);
            if (cnpj.replace(".", "").replace("-", "").replace("/", "").trim().equals("00000000000000")) {
                return false;
            }
            cnpjValidator.assertValid(cnpj.replace(".", "").replace("-", "").replace("/", "").trim());

            //return cnpj.replace(".", "").replace("-", "").replace("/", "").trim().equals("00000000000000");
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "CPF INVALIDO");
            //System.err.println("invalid");
            return false;

        }

    }
    
    public static boolean validaIe(String ie) {
        IESaoPauloValidator ieValidator = new IESaoPauloValidator();

        // se passou pra cá, é porque a data é válida
        try {
            //cpfValidator.assertValid(cnpj);
            
            if (ie.replace(".", "").trim().equals("000000000")) {
                return false;
            }
            ieValidator.assertValid(ie);

            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "CPF INVALIDO");
            //System.err.println("invalid");
            return false;

        }

    }


    public static boolean validaData(String data2) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        String dataString = data2;

        String dataSeparada[] = dataString.split("/");
        String dataInvertida = "";

        dataInvertida += dataSeparada[2];
        dataInvertida += dataSeparada[1];
        dataInvertida += dataSeparada[0];

        try {
            Date data = sdf.parse(dataString);

            return Integer.parseInt(dataInvertida.trim().replace("/", "")) <= Integer.parseInt(getDateTime().trim().replace("/", ""));
            // se passou pra cá, é porque a data é válida
        } catch (ParseException e) {
            // se cair aqui, a data é inválida
            //System.err.println("Data inválida");
            return false;
        }

    }

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        
        return dateFormat.format(date);
    }

    public static boolean validaEmail(String email) {
        if(email.trim().equals("")){
           return true; 
        }else{
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        }
        //return true;
    }

    public static boolean validaNome(String string) {

        return string.trim().equals("");

    }

    public static boolean validaEndereco(String string) {

        return string.trim().equals("");

    }

    public static boolean validaNumero(String string) {
        try {
            
            int teste = Integer.parseInt(string.trim());
            return string.trim().equals("");
        } catch (NumberFormatException e) {
            return true;
        }
        

    }
    
    public static boolean validaDouble(String string) {
        try {
            
            Double teste = Double.parseDouble(string.trim());
            return string.trim().equals("");
        } catch (NumberFormatException e) {
            return true;
        }
        

    }
    
    public static boolean validaBairro(String string) {

        return string.trim().equals("");

    }
    
    public static boolean validaCidade(int inteiro) {

        return inteiro==0;

    }
    
    public static boolean validaComboBox(int inteiro) {

        return inteiro==0;

    }
    
    public static boolean validaCep(String string) {

        return string.trim().replace(".", "").replace("-", "").equals("");

    }
    
    public static boolean validaEstado(int inteiro) {

        return inteiro==0;

    }
    
    public static boolean validaLogin(String string) {

        return string.trim().replace(".", "").replace("-", "").equals("");

    }
    
    public static boolean validaSenha(String string) {

        return string.trim().equals("")||string.equals(null);

    }
    
    public static boolean validaString(String string) {

        return string.trim().equals("")||string.equals(null);

    }
    
}
