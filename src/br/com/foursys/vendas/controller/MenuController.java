/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.util.Mensagem;
import br.com.foursys.vendas.view.MenuPrincipal;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class MenuController {
    
    private MenuPrincipal viewMenu;
    private String login;
     public MenuController(MenuPrincipal viewMenu) {
        this.viewMenu = viewMenu;
    }
     
    public MenuController(String login) {
        this.login = login;
    } 
     
     public MenuController() {
     }
     
    public void buscaLogin(String string){
        viewMenu = new MenuPrincipal();
        this.viewMenu.getJblUsuario().setText(string);
        
    }
    public void encerrando(){
        
         int x = JOptionPane.showConfirmDialog(null, Mensagem.encerra, "Atenção",
            JOptionPane.YES_OPTION,
            JOptionPane.CANCEL_OPTION);
        if ((x == JOptionPane.YES_OPTION)) {
            System.exit(0);
        }
    }
    
}
