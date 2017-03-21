package de.goldmann.tienda.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {

    private static final long     serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String            id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Order             order;

    private String            name;

    private String            img;

    @Enumerated(EnumType.STRING)
    private ProductCategory   category;

    private int               count;

    Product() {}

    public Product(final String id, final String name, final String img, final ProductCategory category,
            final int count) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.category = category;
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Product ["
                + (name != null ? "name=" + name + ", " : "")
                + (img != null ? "img=" + img + ", " : "")
                + (category != null ? "category=" + category + ", " : "")
                + "count="
                + count
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
