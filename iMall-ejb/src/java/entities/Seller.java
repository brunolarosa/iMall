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
    @NamedQuery(name="Seller.findAll", query="SELECT s FROM Seller s"),
    @NamedQuery(name="Seller.findSellerForLogin", query="SELECT s FROM Seller s WHERE s.login = :login")
})
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    
    private String mailAdress;
    private String login;
    private String password;
    
    @OneToMany(mappedBy="seller")
    private List<Shop> shops;

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

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    



    public Seller() {
        this.shops = new ArrayList<Shop>();
    }

    public Seller(String name, String mailAdress, String login, String password) {
        this.name = name;
        this.mailAdress = mailAdress;
        this.login = login;
        this.password = password;
        this.shops = new ArrayList<Shop>();
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
        if (!(object instanceof Seller)) {
            return false;
        }
        Seller other = (Seller) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Seller[ id=" + id + " ]";
    }
    
}
