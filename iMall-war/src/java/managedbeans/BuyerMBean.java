/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
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
    
    public List<Product> getShoppingCart() {
        return shoppingCartManager.getShoppingCart();
    }

}
