/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Shop;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import sessions.ShopManager;

/**
 *
 * @author brunolarosa
 */
@Named(value = "shopMBean")
@SessionScoped
public class ShopMBean implements Serializable {
    
    @EJB
    private ShopManager shopManager;

    
    
    /* FIELDS */
    
    private String name;
    private String address;
    private String postalCode;
    private String town;

    
    
    public ShopMBean() {
    }

    /* GETTERS AND SETTERS */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    
    
    
    /* METHODS */
    public String createShop() {
        
        SellerMBean bean = (SellerMBean) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{sellerMBean}", SellerMBean.class);
        
        if(bean.isConnected() == true && bean.getCurrentSeller()!= null) {
            Shop shop = shopManager.createShop(name, address, postalCode, town, bean.getCurrentSeller());
            bean.setCurrentShop(shop);
            
            this.setName(null);
            this.setAddress(null);
            this.setPostalCode(null);
            this.setTown(null);
            
            return "indexShop?faces-redirect=true";
        }
        
        return "addShop?faces-redirect=true";
    }
    
    
}
