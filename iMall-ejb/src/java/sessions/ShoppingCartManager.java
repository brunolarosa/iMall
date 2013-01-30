/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author brunolarosa
 */
@Stateful
@LocalBean
public class ShoppingCartManager {

    private List<Product> shoppingCart = new ArrayList<Product>();

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }
    
    public void addProduct(Product product) {
        this.getShoppingCart().add(product);
    }
    
    public void deleteProduct(Product product) {
        this.getShoppingCart().remove(product);
    }
    
    @Remove
    public void checkout() {
     shoppingCart.clear();
    }  
}
