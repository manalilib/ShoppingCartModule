import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import static groovy.util.GroovyTestCase.*;

/**
 * Created by manalilib on 1/16/17.
 */
public class ShoppingCartTest {

    String expectedPrice = "";

    public void loadMocks(int scenario) throws Exception {
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

        Cart cart = new Cart(products,promos);
        switch (scenario){
            case 1:testPromoSmallScenario(cart);
                break;
            case 2:
                testPromoLargeScenario(cart);
                break;
            case 3:
                testPromoMediumScenario(cart);
                break;
            case 4:
                testPromoCodeScenario(cart);
                break;
            default:
                System.out.println("Invalid");
        }
    }

    @Test
    public void testSmallGBPromo() throws Exception {
        loadMocks(1);
    }

    @Test
    public void testLargeGBPromo() throws Exception {
        loadMocks(2);
    }

    @Test
    public void testMediumGBPromo() throws Exception {
        loadMocks(3);
    }

    @Test
    public void testWithPromoCode() throws Exception {
        loadMocks(4);
    }

    //Promo scenario methods
    //1st scenario: expected Price: $94.70, 3 x Unlimited 1 GB, 1 x Unlimited 5 GB
    public void testPromoSmallScenario(Cart cart){
        System.out.println("Running validatePromoSmall test..\nexpected Price: $94.70, 3 x Unlimited 1 GB, 1 x Unlimited 5 GB\n ");

            cart.add("ult_small");
            cart.add("ult_small");
            cart.add("ult_small");
            cart.add("ult_large");
            double total = cart.total();
            String roundedTotal = String.format("%.2f",total);
            expectedPrice = "94.70";
        System.out.println("Discounted Price: $" + roundedTotal);
            assertEquals(expectedPrice,roundedTotal);
    }

    //2nd scenario: expected Price: $209.40, 2 x Unlimited 1 GB + 4 x Unlimited 5 GB
    public void testPromoLargeScenario(Cart cart){
        System.out.println("Running testPromoLargeScenario test..\nexpected Price: $209.40, 2 x Unlimited 1 GB + 4 x Unlimited 5 GB\n ");
        cart.add("ult_small");
        cart.add("ult_small");
        cart.add("ult_large");
        cart.add("ult_large");
        cart.add("ult_large");
        cart.add("ult_large");
        double total = cart.total();
        String roundedTotal = String.format("%.2f",total);
        expectedPrice = "209.40";
        System.out.println("Discounted Price: $" + roundedTotal);
//        printPoducts(cart);
        assertEquals(expectedPrice,roundedTotal);
    }

    //3rd scenario: expected Price: $84.70 , 1 x Unlimited 1 GB + 2 X Unlimited 2 GB + 2 X 1 GB Data-pack
    public void testPromoMediumScenario(Cart cart){
        System.out.println("Running testPromoMediumScenario test..\nexpected Price: $84.70 , 1 x Unlimited 1 GB + 2 X Unlimited 2 GB + 2 X 1 GB Data-pack\n");
        cart.add("ult_small");
        cart.add("ult_medium");
        cart.add("ult_medium");
        double total = cart.total();
        String roundedTotal = String.format("%.2f",total);
        expectedPrice = "84.70";
        System.out.println("Discounted Price: $" + roundedTotal);
        assertEquals(expectedPrice,roundedTotal);
    }


    //3rd scenario: expected Price: $31.32 1 x Unlimited 1 GB , 1 x 1 GB Data-pack
    public void testPromoCodeScenario(Cart cart){
        System.out.println("Running testPromoMediumScenario test..\nexpected Price: $84.70 , 1 x Unlimited 1 GB + 2 X Unlimited 2 GB + 2 X 1 GB Data-pack\n");
        cart.add("ult_small");
        cart.add("1gb", "I<3AMAYSIM");
        double total = cart.total();
        String roundedTotal = String.format("%.2f",total);
        expectedPrice = "31.32";
        System.out.println("Discounted Price: $" + roundedTotal);
        assertEquals(expectedPrice,roundedTotal);
    }
//
//    public void printPoducts(Cart cart){
//        cart.items().forEach(prod -> System.out.println("item: " + Collections.frequency(cart.items(),prod.name)));
//    }
}
