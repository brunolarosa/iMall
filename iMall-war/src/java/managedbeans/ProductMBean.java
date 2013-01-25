/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import com.ebay.services.client.ClientConfig;
import entities.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author brunolarosa
 */
@Named(value = "productMBean")
@SessionScoped
public class ProductMBean implements Serializable {

    private Product currentProduct;
    
    private String searchString;
    
    private ClientConfig clientConfig;
    
    
    public ClientConfig getClientConfig() {
        if(null == clientConfig) {
            clientConfig = new ClientConfig();
            clientConfig.setApplicationId("BrunoLAR-3fd0-4703-979c-aa9e1820213f");
        }
        
        return clientConfig;
    }
    
    /**
     * Creates a new instance of ProductMBean
     */
    public ProductMBean() {
    }
    
    
    
    
    
}
