import java.util.ArrayList;
import java.util.HashMap;
import static groovy.util.GroovyTestCase.*;

/**
 * Created by manalilib on 1/16/17.
 */
public class ShoppingCartTest {
    public void main() throws Exception {
        System.out.println("Running Shopping Cart Module Test..\n\n");
        @org.junit.runners.Parameterized.Parameters
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
                products.get("ult_large"), //prod_type
                new ArrayList<Product>(), //freebies
                .11135857461024,
                3,
                false);
        promos.add(promo_large);

        //PROMO 1gb
        ArrayList<Product> freeProductsPromoC = new ArrayList<Product>();
        freeProductsPromoC.add(products.get("1gb"));//prod_type

        Promotions promo_med = new Promotions(
                products.get("ult_medium"),//prod_type
                freeProductsPromoC,//freebies
                0,//discount
                1, //promo qty requirement
                true);
        promos.add(promo_med);


        Cart cart = new Cart(products, promos);

        //1st scenario: $94.70, 3 x Unlimited 1 GB, 1 x Unlimited 5 GB
        @org.junit.Test
        public void testPromoSmallScenario{
            cart.add("ult_small");
            cart.add("ult_small");
            cart.add("ult_small");
            cart.add("ult_large");
            double total = cart.total();
            assertEquals("total Price check: " + 94.70, total );
        }
    }
}
