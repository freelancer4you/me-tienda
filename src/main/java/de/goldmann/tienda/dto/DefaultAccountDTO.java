package de.goldmann.tienda.dto;

import java.io.Serializable;

public class DefaultAccountDTO implements Serializable {

    private static final long serialVersionUID = -1973241612748624323L;

    private String            salutation;
    private String            title;
    private String            firstName;
    private String            lastName;
    private String            email;
    private String            password;
    private String            phoneNumber;
    private Address           address;
    private String            registrationDate;

    public DefaultAccountDTO() {}

    public DefaultAccountDTO(final String salutation, final String title, final String firstName, final String lastName,
            final String email,
            final String password, final String phoneNumber, final Address address, final String registration) {
        this.salutation = salutation;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        registrationDate = registration;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "DefaultAccountDTO ["
                + (salutation != null ? "salutation=" + salutation + ", " : "")
                + (title != null ? "title=" + title + ", " : "")
                + (firstName != null ? "firstName=" + firstName + ", " : "")
                + (lastName != null ? "lastName=" + lastName + ", " : "")
                + (email != null ? "email=" + email + ", " : "")
                + (password != null ? "password=" + password + ", " : "")
                + (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
                + (address != null ? "adress=" + address + ", " : "")
                + (registrationDate != null ? "registrationDate=" + registrationDate : "")
                + "]";
    }

}
