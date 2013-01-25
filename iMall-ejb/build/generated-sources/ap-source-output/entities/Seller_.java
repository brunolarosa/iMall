package entities;

import entities.Shop;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-01-24T15:18:26")
@StaticMetamodel(Seller.class)
public class Seller_ { 

    public static volatile SingularAttribute<Seller, Integer> id;
    public static volatile ListAttribute<Seller, Shop> shops;
    public static volatile SingularAttribute<Seller, String> name;
    public static volatile SingularAttribute<Seller, String> mailAdress;
    public static volatile SingularAttribute<Seller, String> login;
    public static volatile SingularAttribute<Seller, String> password;

}