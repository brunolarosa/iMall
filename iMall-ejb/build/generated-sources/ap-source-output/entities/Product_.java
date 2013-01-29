package entities;

import entities.Shop;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-01-29T15:51:39")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Shop> shop;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, String> imageUrl;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Integer> quantity;

}