/**
 * Created by manalilib on 1/9/17.
 */
import java.util.*;

public class ShoppingCart {
public static void  main(String[] args) {

    HashMap<String, Product> products = new HashMap<String, Product>();
    products.put("ult_small", new Product("ult_small", "Unlimited 1GB", 24.90));
    products.put("ult_medium", new Product("ult_medium", "Unlimited 2GB", 29.90));
    products.put("ult_large", new Product("ult_large", "Unlimited 5GB", 44.90));
    products.put("1gb", new Product("1gb", "1 GB Data-pack", 9.90));
    ArrayList<Promotions> promos = new ArrayList<Promotions>();

    //PROMO Maintenance
    //PROMO ult_small
    Promotions promo_small = new Promotions(
            products.get("ult_small"), //prod_type
            new ArrayList<Product>(), //freebies
            .333333, // disc: 100% = 1, 50% = .5
            3,  //promo qty requirement
            false);
    promos.add(promo_small);

    //PROMO ult_large
    Promotions promo_large = new Promotions(
            products.get("ult_large"),
            new ArrayList<Product>(),
            .11135857461024,
            3,
            false);
    promos.add(promo_small);

    //PROMO 1gb
    ArrayList<Product> freeProductsPromoC = new ArrayList<Product>();
    freeProductsPromoC.add(products.get("1gb"));

    Promotions promo_med = new Promotions(
            products.get("ult_medium"),
            freeProductsPromoC,
            0,
            1,
            true);
    promos.add(promo_med);


    Cart cart = new Cart(products, promos);

    // Scenario 1
//    cart.add("ult_small");
//    cart.add("ult_small");
//    cart.add("ult_small");
//    cart.add("ult_large");

    // Scenario 2
    //cart.add("ult_small");
    //cart.add("ult_small");
    //cart.add("ult_large");
    //cart.add("ult_large");
    //cart.add("ult_large");
    //cart.add("ult_large");

    // Scenario 3
    //cart.add("ult_small");
    //cart.add("ult_medium");
    //cart.add("ult_medium");

//    //Scenario 4
//    cart.add("ult_small");
//    cart.add("1gb", "I<3AMAYSIM");

    System.out.println("total price: " + cart.total());
    cart.items().forEach(prod -> System.out.println("item: " + prod.name));
}
}
