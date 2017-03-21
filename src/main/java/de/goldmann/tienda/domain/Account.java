package de.goldmann.tienda.domain;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import de.goldmann.tienda.dto.AccountDTO;
import de.goldmann.tienda.dto.Address;

@Entity
public class Account extends UserId {

    private static final long serialVersionUID = -247943674703330516L;

    @Embedded
    private PostAdress adresse;

    @OneToMany(mappedBy = "account")
    private Set<Order> orders;

    Account() {
    }

    public Account(final AccountDTO acc) {
        this(acc, UserRole.USER);
    }

    public Account(final AccountDTO acc, final UserRole role) {
        super(acc.getEmail(), role);
        final Address address = acc.getAddress();
        adresse = new PostAdress(address.getStreet(),
                address.getZipcode(),
                address.getCity(),
                address.getHouseNr(),
                address.getPhoneNumber());
    }

    public PostAdress getAdresse() {
        return adresse;
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

}
