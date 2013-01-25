/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seller;
import entities.Shop;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author brunolarosa
 */
@Stateless
@LocalBean
public class ShopManager {
    @PersistenceContext(unitName = "iMall-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Shop> getAllShops() {
        return null;
    }
    
    

    
    
}
