/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Seller;
import entities.Shop;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import sessions.SellerManager;

/**
 *
 * @author brunolarosa
 */
@Named(value = "sellerMBean")
@SessionScoped
public class SellerMBean implements Serializable {
    
    private String name;
    private String mailAdress;
    private String login;
    private String password;
    
    private boolean connected = false;
    
    private Seller currentSeller;
    private Shop currentShop;

    public Seller getCurrentSeller() {
        return currentSeller;
    }

    public void setCurrentSeller(Seller currentSeller) {
        this.currentSeller = currentSeller;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }    
    
    
    
    @EJB
    private SellerManager sellerManager;

    /**
     * Creates a new instance of SellerMBean
     */
    public SellerMBean() {
    }
    
    public List<Seller> getAllSellers() {
        return sellerManager.getAllSellers();
    }
    
    public Seller getSellerForId(int id) {
        return sellerManager.getSellerForId(id);
    }
    
    
    
    public String signUp() {
        sellerManager.createSeller(name, login, password, mailAdress);
        name = null;
        login = null;
        password = null;
        mailAdress = null;
        
        return "index.xhtml?faces-redirect=true";
    }
    public void signIn() {
        
        
        Seller seller = sellerManager.getSellerForLogin(login);
        
        if(null != seller) {
            if(seller.getPassword().equals(password)) {
                this.setConnected(true);
                login = null;
                password = null;
                currentSeller = seller;
            }
        }
    }
    
    
    
}
