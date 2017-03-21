package de.goldmann.tienda.dto;

import java.io.Serializable;


public class AccountDTO implements Serializable {

    private static final long serialVersionUID = -3682829511872578712L;

    private String            email;
    private Address           address;

    public AccountDTO() {}

    public AccountDTO(final String email, final Address address) {
        super();
        this.email = email;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }



}
