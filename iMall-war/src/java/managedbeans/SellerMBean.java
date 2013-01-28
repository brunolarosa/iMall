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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import sessions.SellerManager;
import sessions.ShopManager;

/**
 *
 * @author brunolarosa
 */
@Named(value = "sellerMBean")
@SessionScoped
public class SellerMBean implements Serializable {
    
    @EJB
    private SellerManager sellerManager;
    
    @EJB
    private ShopManager shopManager;
    
    
    /* FIELDS */
    private String name;
    private String mailAddress;
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

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
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

    public Shop getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(Shop currentShop) {
        this.currentShop = currentShop;
    }
    
    
    

    
    

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
    
    public String showShop(Shop shop) {
        this.setCurrentShop(shop);
        return "indexShop?faces-redirect=true";
    }
    
    public String signUp() {
        sellerManager.createSeller(name, login, password, mailAddress);
        name = null;
        login = null;
        password = null;
        mailAddress = null;
        
        return "index.xhtml?faces-redirect=true";
    }
    
    
    public String signIn() {
        
        
        Seller seller = sellerManager.getSellerForLogin(login);
        
        if(null != seller) {
            if(seller.getPassword().equals(password)) {
                this.setConnected(true);
                login = null;
                password = null;
                currentSeller = seller;
            }
        }
        
        return "index.xhtml?faces-redirect=true";
       
    }
    
    public String lougOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../index?faces-redirect=true";
    }
    
    
}
