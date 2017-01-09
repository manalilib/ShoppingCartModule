import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by manalilib on 1/9/17.
 */

public class Promotions {

    ArrayList<Product> freebies;
    double discount;
    Product product;
    boolean isMod;
    int promoQty;


    public Promotions(Product product, ArrayList<Product> freebies,
                           double discount, int promoQty, boolean isMod) {
        this.product = product;
        this.freebies = freebies;
        this.discount = discount;
        this.promoQty = promoQty;
        this.isMod = isMod;
    }

    public PromoFreebies getPromoResult(ArrayList<Product> addedProducts) {
        double totalDeduction = 0;
        ArrayList<Product> totalFreebies = new ArrayList<Product>();
        int targetProductSize = addedProducts.stream().filter(p -> p.code == product.code).collect(Collectors.toList()).size();
        if (isMod) {
            int multiplier = targetProductSize / promoQty;
            for (int i = 0; i < multiplier; i++) {
                totalDeduction = (discount * product.price) * targetProductSize;
                totalFreebies.addAll(freebies);
            }
            return new PromoFreebies(totalFreebies, totalDeduction);
        } else {
            if (targetProductSize >= promoQty) {
                totalDeduction = (discount * product.price) * targetProductSize;
                totalFreebies.addAll(freebies);
            }
        }
        return new PromoFreebies(totalFreebies, totalDeduction);
    }
}
