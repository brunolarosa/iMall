/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seller;
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
public class SellerManager {
    
    /* PERSISTENCE UNIT */
    @PersistenceContext(unitName = "iMall-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    /* QUERY */

    public List<Seller> getAllSellers() {
        Query query = em.createNamedQuery("Seller.findAll");
        return query.getResultList();
    }
    
    public Seller getSellerForId(int id) {
        return em.find(Seller.class, id);
    }
    
    public Seller getSellerForLogin(String login) {
        Query query = em.createNamedQuery("Seller.findSellerForLogin");
        query.setParameter("login", login);
        
        if(query.getResultList().size() != 1) {
            return null;
        } else {
            return (Seller) query.getSingleResult();
        }
        
    }

    public void createSeller(String name, String login, String password, String mailAdress) {
        
        Seller seller = new Seller(name, mailAdress, login, password);
        persist(seller);
        
    }
    
    
    
}
