package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.model.Funcionario;
import br.com.foursys.vendas.util.Mensagem;
import br.com.foursys.vendas.util.Valida;
import br.com.foursys.vendas.view.LoginPrincipal;
import br.com.foursys.vendas.view.MenuPrincipal;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dmunhoz
 */
public class LoginController {
    private LoginPrincipal viewLogin;
    
    public LoginController(LoginPrincipal viewLogin){
        this.viewLogin = viewLogin;
    }
    
    public void loginSistema(){
        
        if (Valida.validaString(this.viewLogin.getJtfLogin().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.loginInvalido);
        }else{
            List<Funcionario> listaFuncionario = new FuncionarioController().buscarTodos(this.viewLogin.getJtfLogin().getText());
            int contLogin = 0;
            int contSenha = 0;
            for (Funcionario funcionario : listaFuncionario) {
                contLogin++;
                if (funcionario.getSenha().equals(this.viewLogin.getJtfSenha().getText())) {
                    
                    //System.out.println(funcionario.getLogin());
                    
                    this.viewLogin.dispose();
                    MenuController controllerMenu = new MenuController();
                    controllerMenu.buscaLogin(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
                    
                    
                }else{
                    contSenha++;
                }
            }
            if (Valida.validaComboBox(contLogin)) {
                JOptionPane.showMessageDialog(null, Mensagem.loginInvalido);
            }else if (contSenha > 0) {
                JOptionPane.showMessageDialog(null, Mensagem.senhaInvalida);
            }
            
        }
        
        
    }
    
    
    
    
}
