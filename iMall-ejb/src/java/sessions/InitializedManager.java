/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seller;
import entities.Shop;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author brunolarosa
 */
@Singleton
@Startup
@LocalBean
public class InitializedManager {
    @PersistenceContext(unitName = "iMall-ejbPU")
    private EntityManager em;

    
    
    @PostConstruct
    public void init() {
        
        Seller seller = new Seller("Bruno LAROSA", "bru.larosa@gmail.com", "brunolarosa", "123456");
        persist(seller);
        Shop shop = new Shop("MyStore", "23 chemin des Martelles", "06620", "Le Bar sur Loup", seller);
        seller.getShops().add(shop);
        em.merge(seller);
        persist(shop);
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
