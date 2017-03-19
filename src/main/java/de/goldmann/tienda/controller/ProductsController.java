package de.goldmann.tienda.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.goldmann.tienda.domain.Product;
import de.goldmann.tienda.domain.ProductCategory;

@RestController
public class ProductsController {

    final Set<Product> allProducts = new HashSet<>();

    public ProductsController() {
        allProducts.add(new Product("banane", "Banane", "banane.png", ProductCategory.FRUITSANDVEGETABLES, 0));
        allProducts.add(new Product("birne", "Birne", "birne.png", ProductCategory.FRUITSANDVEGETABLES, 0));
        allProducts.add(new Product("apfel", "Apfel", "apfel.png", ProductCategory.FRUITSANDVEGETABLES, 0));
        allProducts.add(new Product("tomate", "Tomate", "tomate.png", ProductCategory.FRUITSANDVEGETABLES, 0));
        allProducts.add(new Product("gurke", "Gurke", "gurke.png", ProductCategory.FRUITSANDVEGETABLES, 0));
        allProducts.add(new Product("kirsche", "Kirsche", "kirsche.png", ProductCategory.FRUITSANDVEGETABLES, 0));

        allProducts.add(new Product("brot", "Brot", "brot.png", ProductCategory.BREADANDPASTRIES, 0));
    }

    @RequestMapping(value = "/api/resources/products", method = RequestMethod.GET)
    public Set<Product> getProducts(@RequestParam("category") final String categoryStr) {
        try {
            final ProductCategory category = ProductCategory.valueOf(categoryStr);
            System.out.println("Lade Prdoukte für Category: " + category);
            final Set<Product> result = new HashSet<>();
            for (final Product product : allProducts) {
                if (product.getCategory().equals(category)) {
                    result.add(product);
                }
            }
            return result;
        }
        catch (final IllegalArgumentException e) {
            System.err.println(e);
            throw e;
        }
    }

    // TOOD diese Resources ist noch nicht ungeschützt
    @RequestMapping(value = "/api/resources/categories", method = RequestMethod.GET)
    public Set<CategoryWrapper> getCategories() {
        final Set<CategoryWrapper> categories = new HashSet<>();
        for (final ProductCategory category : ProductCategory.values()) {
            categories.add(new CategoryWrapper(category));
        }
        return categories;
    }

}