package de.goldmann.tienda.controller;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.goldmann.tienda.dao.ProductRepository;
import de.goldmann.tienda.domain.Product;
import de.goldmann.tienda.domain.ProductCategory;

@RestController
public class ProductsController {

    private static final Logger LOGGER      = LoggerFactory.getLogger(AccountController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductsController(final ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository, "productRepository");
    }

    @RequestMapping(value = "/api/resources/products", method = RequestMethod.GET)
    public Set<Product> getProducts(@RequestParam("category") final String categoryStr) {
        try {
            final ProductCategory category = ProductCategory.valueOf(categoryStr);
            return productRepository.findByCategory(category);
        }
        catch (final IllegalArgumentException e) {
            LOGGER.error("Fehler beim Laden der Produkte für Category: " + categoryStr, e);
            throw e;
        }
    }

    @RequestMapping(value = "/api/resources/categories", method = RequestMethod.GET)
    public Set<CategoryWrapper> getCategories() {
        final Set<CategoryWrapper> categories = new HashSet<>();
        for (final ProductCategory category : ProductCategory.values()) {
            categories.add(new CategoryWrapper(category));
        }
        return categories;
    }

}
