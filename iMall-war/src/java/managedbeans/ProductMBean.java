/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.*;
import entities.Product;
import entities.Category;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CategoryManager;
import sessions.ProductManager;

/**
 *
 * @author brunolarosa
 */
@Named(value = "productMBean")
@SessionScoped
public class ProductMBean implements Serializable {

    @EJB
    private CategoryManager categoryManager;
    @EJB
    private ProductManager productManager;
    
    
    private String name;
    private String imageUrl;
    private String description;
    private double price;
    private int quantity;
    private String keywords;
    private String categoryName;
    private boolean itemSelected = false;
    private Product currentProduct;
    private List<Product> categoryProducts;
    private SearchItem selectedSearchItem;
    private String searchString;
    private SellerMBean sellerMBean;

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

    public SellerMBean getSellerMBean() {
        if (null == sellerMBean) {
            sellerMBean = (SellerMBean) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{sellerMBean}", SellerMBean.class);
        }
        return sellerMBean;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Product> getCategoryProducts() {
        return categoryProducts;
    }

    public void setCategoryProducts(List<Product> categoryProducts) {
        this.categoryProducts = categoryProducts;
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

    private FindingServicePortType getServiceClient() {

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

        this.setSearchString(null);
        this.setItemSelected(false);

        return "addProduct?faces-redirect=true";

    }

    public String selectSearchItem(SearchItem item) {

        this.setImageUrl(item.getGalleryURL());
        this.setName(item.getTitle());
        this.setDescription(item.getSubtitle());
        this.setPrice(0);
        this.setQuantity(1);
        this.setCategoryName(item.getPrimaryCategory().getCategoryName());
        this.setKeywords(null);
        this.setSearchItems(null);
        this.setItemSelected(true);
        return "addProduct?faces-redirect=true";
    }

    public String createProduct() {

        if (this.getSellerMBean().isConnected()
                == true && this.getSellerMBean().getCurrentSeller() != null) {

            if (this.isItemSelected()) {
                this.currentProduct = productManager.createProduct(name, this.getSellerMBean().getCurrentShop(), imageUrl, description, price, quantity, keywords, categoryManager.getCategoryForName(categoryName));
                this.setImageUrl(null);
                this.setName(null);
                this.setDescription(null);
                this.setPrice(0);
                this.setQuantity(0);
                this.setCategoryName(null);
                this.setKeywords(null);
                this.setSearchItems(null);
                this.setItemSelected(false);
            }
        }

        return "indexShop?faces-redirect=true";
    }

    public String showProduct(Product product) {

        if (this.getSellerMBean().isConnected() && product.getShop().getSeller().equals(this.getSellerMBean().getCurrentSeller())) {

            this.setCurrentProduct(product);

        } else {
        }



        return "productInfo?faces-redirect=true";
    }

    public String editProduct() {
        if (this.getSellerMBean().isConnected() && this.getCurrentProduct().getShop().getSeller().equals(this.getSellerMBean().getCurrentSeller())) {

            productManager.mergeProduct(this.getCurrentProduct());

        } else {
        }

        return "indexShop?faces-redirect=true";
    }
    
    public String deleteProduct(Product product) {
       
        if (this.getSellerMBean().isConnected() && product.getShop().getSeller().equals(this.getSellerMBean().getCurrentSeller())) {

            
            productManager.removeProduct(product);

        } else {
        }
        return "indexShop?faces-redirect=true";
    }
    
    public String showCategory(Category category) {
        this.setCategoryProducts(this.getProductsForCategory(category));
        return "indexCategory?faces-redirect=true";
    }
    
    public List<Product> getProductsForCategory(Category category) {
        return productManager.getProductsForCategory(category);
    }
    
    public List<entities.Category> getAllCategories() {
        return categoryManager.getAllCategories();
    }
}
