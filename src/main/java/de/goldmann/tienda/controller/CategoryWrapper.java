package de.goldmann.tienda.controller;

import java.util.Objects;

import de.goldmann.tienda.domain.ProductCategory;

public class CategoryWrapper {

    private final String name;
    private final String caption;

    public CategoryWrapper(final ProductCategory category) {
        Objects.requireNonNull(category, "Param 'category' cannot be null");
        this.name = category.name();
        this.caption = category.getCaption();
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return "CategoryWrapper ["
                + (name != null ? "name=" + name + ", " : "")
                + (caption != null ? "caption=" + caption : "")
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
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
        final CategoryWrapper other = (CategoryWrapper) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
