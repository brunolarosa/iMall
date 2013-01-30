/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author brunolarosa
 */
@Stateless
@LocalBean
public class CategoryManager {
    
    /* ENTITY MANAGER */
    @PersistenceContext(unitName = "iMall-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    /* BUSINESS METHODS */
    private Category createCategory(String name) {
        Category category = new Category(name);
        persist(category);
        return category;
    }
    
    public List<Category> getAllCategories() {
        Query query = em.createNamedQuery("Category.findAll");
        return query.getResultList();
    }
    
    public Category getCategoryForName(String name) {
       Query query = em.createNamedQuery("Category.findForName");
       query.setParameter("name", name);
       Category category = null;
        try {
            category = (Category) query.getSingleResult();
        } catch (NoResultException e) {
            category = createCategory(name);
        } finally {
            return category;
        }
    }
    
    
    

    
}
