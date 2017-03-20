package de.goldmann.tienda.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PostAdress implements Serializable {

    private static final long serialVersionUID = 3071306697887822761L;

    public static final int MAXLEN_STREET = 82;
    public static final int MAXLEN_POSTCODE = 10;
    public static final int MAXLEN_CITY = 40;
    private static final int  MAXLEN_HOUSE_NR  = 4;
    private static final int  MAXLEN_PHONE_NR  = 4;

    @Column(name = "street", nullable = false, length = MAXLEN_STREET)
    private String street;

    @Column(name = "zipcode", nullable = false, length = MAXLEN_POSTCODE)
    private String            zipcode;

    @Column(name = "city", nullable = false, length = MAXLEN_CITY)
    private String city;

    @Column(name = "housenr", nullable = false, length = MAXLEN_HOUSE_NR)
    private String            houseNr;

    @Column(name = "phonenumber", nullable = false, length = MAXLEN_PHONE_NR)
    private String            phoneNumber;

    // JPA-Konstruktor
    PostAdress() {
        super();
    }

    public PostAdress(final String street, final String zipcode, final String city, final String houseNr,
            final String phoneNumber) {
        super();
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.houseNr = houseNr;
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "PostAdress ["
                + (street != null ? "street=" + street + ", " : "")
                + (zipcode != null ? "zipcode=" + zipcode + ", " : "")
                + (city != null ? "city=" + city + ", " : "")
                + (houseNr != null ? "houseNr=" + houseNr + ", " : "")
                + (phoneNumber != null ? "phoneNumber=" + phoneNumber : "")
                + "]";
    }

}
