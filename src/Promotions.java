import java.util.ArrayList;

/**
 * Created by manalilib on 1/9/17.
 */

public class Promotions {

    Product targetProduct;

    ArrayList<Product> freeProducts;
    double discount;

    boolean isMod;
    int quantityQualifier;


    public Promotions(Product targetProduct, ArrayList<Product> freeProducts,
                           double discount, int quantityQualifier, boolean isMod) {
        this.targetProduct = targetProduct;
        this.freeProducts = freeProducts;
        this.discount = discount;
        this.quantityQualifier = quantityQualifier;
        this.isMod = isMod;
    }

}
