import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by manalilib on 1/9/17.
 */
public class PromoFreebies {
    double totalDeduction;
    ArrayList<Product> totalFreebies;

    public PromoFreebies(ArrayList<Product> totalFreebies, double totalDeduction) {
        this.totalFreebies = totalFreebies;
        this.totalDeduction = totalDeduction;
    }


}
