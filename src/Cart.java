/**
 * Created by manalilib on 1/9/17.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    HashMap<String, Product> products;
    ArrayList<Product> addedProducts;
    ArrayList<Product> freebies;
    ArrayList<Promotions> promos;
    String currentPromoCode = "I<3AMAYSIM";
    boolean isPromoCode; //promo flag
    double discount;

    public Cart(HashMap<String, Product> products, ArrayList<Promotions> promos) {
        this.products = products;
        this.promos = promos;
        addedProducts = new ArrayList<Product>();
        this.isPromoCode = false;
    }

//	public void add(String productCode,Optional<String> promoCode) {
//		if (products.get(productCode) != null) addedProducts.add(products.get(productCode));
//		if (promoCode.isPresent() && promoCode.equals(currentPromoCode)) isPromoCode = true;
//        else isPromoCode = false;
//	} Works with Scala but not in Java :(

    public void add(String productCode) {
        if (products.get(productCode) != null) addedProducts.add(products.get(productCode));
    }

    public void add(String productCode, String promoCode) {
        if (products.get(productCode) != null) addedProducts.add(products.get(productCode));
        if (promoCode.equals(currentPromoCode)) isPromoCode = true;
    }

    public double total() {
        if (freebies == null) validatePromo();

        double total = addedProducts.stream().mapToDouble(product -> product.price).sum();
        System.out.println("Price: " + total);
        double totalResult = total - discount;
        if (this.isPromoCode) {
            discount = .10;
            System.out.println("Promocode: " + currentPromoCode + "\nless : " + discount);
            return totalResult - (totalResult * discount);
        } else return totalResult;
    }

    private void validatePromo() {
        discount = 0;
        freebies = new ArrayList<Product>();
        for (int i = 0; i < promos.size(); i++) {
            PromoFreebies promoFreebies = promos.get(i).getPromoResult(addedProducts);
            discount += promoFreebies.totalDeduction;
            freebies.addAll(promoFreebies.totalFreebies);
        }
        System.out.println("freebies:"  + freebies.size()+ "\n" +"discount: " + discount);
    }

    public ArrayList<Product> items() {
        if (freebies == null) validatePromo();
        ArrayList<Product> result = addedProducts;
        result.addAll(freebies);
        return result;
    }
}
