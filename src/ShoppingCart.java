/**
 * Created by manalilib on 1/9/17.
 */
import java.util.*;

public class ShoppingCart {
public static void  main(String[] args){
    HashMap<String, Product> products = new HashMap<String, Product>();
    products.put("ult_small", new Product("ult_small", "Unlimited 1GB", 24.90));
    products.put("ult_medium", new Product("ult_medium", "Unlimited 2GB", 29.90));
    products.put("ult_large", new Product("ult_large", "Unlimited 5GB", 44.90));
    products.put("1gb", new Product("1gb", "1 GB Data-pack", 9.90));

}
}
