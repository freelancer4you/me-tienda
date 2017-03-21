package de.goldmann.tienda.dto;

public class Address {
    private String street;
    private String zipcode;
    private String city;
    private String houseNr;
    private String phoneNumber;

    Address() {
        super();
    }

    public Address(final String street, final String zipcode, final String city, final String houseNr,
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

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public void setZipcode(final String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setHouseNr(final String houseNr) {
        this.houseNr = houseNr;
    }

}
