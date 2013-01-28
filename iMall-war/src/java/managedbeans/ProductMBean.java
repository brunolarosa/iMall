/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.*;
import entities.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ProductManager;

/**
 *
 * @author brunolarosa
 */
@Named(value = "productMBean")
@SessionScoped
public class ProductMBean implements Serializable {
    @EJB
    private ProductManager productManager;
    
    private String name;
    private String imageUrl;
    private String description;
    private double price;
    private int quantity;
    private boolean itemSelected = false; 

    private Product currentProduct;
    private SearchItem selectedSearchItem;
    private String searchString;

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public List<SearchItem> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public SearchItem getSelectedSearchItem() {
        return selectedSearchItem;
    }

    public void setSelectedSearchItem(SearchItem selectedSearchItem) {
        this.selectedSearchItem = selectedSearchItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        this.itemSelected = itemSelected;
    }
    
    
    
    /*
     * EBAY WS FIELDS
     */
    private ClientConfig clientConfig;
    private FindingServicePortType serviceClient;
    private List<SearchItem> searchItems;

    public ClientConfig getClientConfig() {
        if (null == clientConfig) {
            clientConfig = new ClientConfig();
            clientConfig.setApplicationId("BrunoLAR-3fd0-4703-979c-aa9e1820213f");
        }

        return clientConfig;
    }

    public FindingServicePortType getServiceClient() {

        if (null == serviceClient) {

            serviceClient = FindingServiceClientFactory.getServiceClient(this.getClientConfig());

        }

        return serviceClient;
    }

    /**
     * Creates a new instance of ProductMBean
     */
    public ProductMBean() {
    }

    public String searchMatchingItems() {

        FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();
        request.setKeywords(this.getSearchString());
        FindItemsByKeywordsResponse result = this.getServiceClient().findItemsByKeywords(request);
        this.setSearchItems(result.getSearchResult().getItem());

        this.setSearchString("");
        this.setItemSelected(false);

        return "addProduct?faces-redirect=true";

    }

    public String selectSearchItem(SearchItem item) {

        this.setImageUrl(item.getGalleryURL());
        this.setName(item.getTitle());
        this.setDescription(item.getSubtitle());
        this.setPrice(0);
        this.setQuantity(1);
        this.setSearchItems(null);
        this.setItemSelected(true);
        return "addProduct?faces-redirect=true";
    }

    public String createProduct() {

        SellerMBean bean = (SellerMBean) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{sellerMBean}", SellerMBean.class);

        if (bean.isConnected()
                == true && bean.getCurrentSeller() != null) {

            if (this.isItemSelected()) {
                this.currentProduct = productManager.createProduct(name, bean.getCurrentShop(), imageUrl, description, price, quantity);
            }
        }

        return "indexShop?faces-redirect=true";
    }
}
