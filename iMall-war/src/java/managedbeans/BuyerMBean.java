/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Product;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import sessions.ShoppingCartManager;

/**
 *
 * @author brunolarosa
 */
@Named(value = "buyerMBean")
@SessionScoped
public class BuyerMBean implements Serializable {
    
    @EJB
    private ShoppingCartManager shoppingCartManager;

    
    
    public BuyerMBean() {
    }
    
    public String addProductToShoppingCart(Product product) {
        shoppingCartManager.addProduct(product);
        return "";
    }
    public String deleteProduct(Product product) {
        shoppingCartManager.deleteProduct(product);
        return "";
    }
    
    public List<Product> getShoppingCart() {
        return shoppingCartManager.getShoppingCart();
    }
    
    public String showShoppingCart() {
        
        return "";
    }

}
