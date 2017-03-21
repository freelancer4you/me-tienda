package de.goldmann.tienda.dto;

import java.io.Serializable;
import java.util.Set;

import de.goldmann.tienda.domain.Product;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public String             client;
    public Set<Product>       cart;
    public long               date;

    OrderDTO() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(final String client) {
        this.client = client;
    }

    public Set<Product> getCart() {
        return cart;
    }

    public void setCart(final Set<Product> cart) {
        this.cart = cart;
    }

    public long getDate() {
        return date;
    }

    public void setDate(final long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderDTO ["
                + (client != null ? "client=" + client + ", " : "")
                + (cart != null ? "cart=" + cart + ", " : "")
                + "date="
                + date
                + "]";
    }

}
