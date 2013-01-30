/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author brunolarosa
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Shop.findAll", query="SELECT s FROM Shop s ORDER BY s.name"),
    @NamedQuery(name="Shop.finAllShopsForSeller", query="SELECT s FROM Shop s WHERE s.seller = :seller")
})
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    private String address;
    private String postalCode;
    private String town;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch= FetchType.EAGER)
    private Seller seller;
    
    @OneToMany(mappedBy="shop")
    private List<Product> products;

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    
    
    
    

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Shop(String name, String address, String postalCode, String town, Seller seller) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.town = town;
        this.seller = seller;
        this.products = new ArrayList<Product>();
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shop)) {
            return false;
        }
        Shop other = (Shop) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Shop[ id=" + id + " ]";
    }
    
}
