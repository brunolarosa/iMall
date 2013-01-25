package entities;

import entities.Product;
import entities.Seller;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-01-24T15:18:26")
@StaticMetamodel(Shop.class)
public class Shop_ { 

    public static volatile SingularAttribute<Shop, Integer> id;
    public static volatile SingularAttribute<Shop, Seller> seller;
    public static volatile ListAttribute<Shop, Product> products;

}