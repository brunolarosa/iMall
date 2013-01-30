/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Seller;
import entities.Shop;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.netbeans.saas.RestResponse;
import org.netbeans.saas.google.GoogleMapService;
import sessions.ShopManager;
import org.netbeans.saas.google.GoogleGeocodingService;
import org.netbeans.saas.google.GoogleMapService.GeoCoder;

/**
 *
 * @author brunolarosa
 */
@Named(value = "shopMBean")
@SessionScoped
public class ShopMBean implements Serializable {

    @EJB
    private ShopManager shopManager;
    /*
     * FIELDS
     */
    private String name;
    private String address;
    private String postalCode;
    private String town;
    private Shop currentShop;

    public ShopMBean() {
    }

    /*
     * GETTERS AND SETTERS
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Shop getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(Shop currentShop) {
        this.currentShop = currentShop;
    }

    public List<Shop> getAllShops() {
        return shopManager.getAllShops();
    }

    public String showShop(Shop shop) {
        this.setCurrentShop(shop);
        return "indexShop?faces-redirect=true";
    }

    /*
     * METHODS
     */
    public String createShop() {

        SellerMBean bean = (SellerMBean) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{sellerMBean}", SellerMBean.class);

        if (bean.isConnected() == true && bean.getCurrentSeller() != null) {
            Shop shop = shopManager.createShop(name, address, postalCode, town, bean.getCurrentSeller());
            bean.setCurrentShop(shop);

            this.setName(null);
            this.setAddress(null);
            this.setPostalCode(null);
            this.setTown(null);

            return "indexShop?faces-redirect=true";
        }

        return "addShop?faces-redirect=true";
    }

    
    public String showGoogleMap(Shop shop) {

        try {
            String address = shop.getAddress() + ", " + shop.getTown();
            java.lang.Integer zoom = 15;
            String iframe = "true";



            RestResponse result = GoogleMapService.getGoogleMap(address, zoom, iframe);
            return result.getDataAsString();
        } catch (Exception ex) {
        }

        return "";





    }
}
