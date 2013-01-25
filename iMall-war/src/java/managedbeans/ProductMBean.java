/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

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

    /**
     * Creates a new instance of ProductMBean
     */
    public ProductMBean() {
    }
}
