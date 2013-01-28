/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Product;
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
public class ProductManager {

    @PersistenceContext(unitName = "iMall-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Product> getAllProductsFromShop(Shop shop) {
        Query query = em.createNamedQuery("Product.findProductsForShop");
        query.setParameter("shop", shop);
        return query.getResultList();
    }

    public Product createProduct(String name, Shop shop, String imageUrl, String description, double price, int quantity) {

        Product product = new Product(name, shop, imageUrl, description, price, quantity);

        shop.getProducts().add(product);
        persist(product);

        return product;
    }
}
