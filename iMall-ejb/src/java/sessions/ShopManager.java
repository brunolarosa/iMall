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
import javax.persistence.Query;

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
        Query query = em.createNamedQuery("Shop.findAll");
        return query.getResultList();
    }
    
    public Shop createShop(String name, String address, String postalCode, String town, Seller seller) {
        Shop shop = new Shop(name, address, postalCode, town, seller);
        seller.getShops().add(shop);
        persist(shop);
        
        return shop;
    }
    
    

    
    
}
