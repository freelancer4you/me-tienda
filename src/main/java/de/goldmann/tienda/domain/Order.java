package de.goldmann.tienda.domain;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Account account;

    private long orderDate;

    @OneToMany(mappedBy = "order")
    private Set<Product> products;

    Order() {}

    public Order(final Account account, final long orderDate, final Set<Product> products) {
        super();
        this.account = account;
        this.orderDate = orderDate;
        this.products = products;
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public long getOrderDate() {
        return orderDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ id >>> 32);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Order other = (Order) obj;
        if (id != other.id)
        {
            return false;
        }
        return true;
    }

}
