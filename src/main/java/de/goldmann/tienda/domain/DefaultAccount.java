package de.goldmann.tienda.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.goldmann.tienda.dto.Address;
import de.goldmann.tienda.dto.DefaultAccountDTO;

@Entity
@DiscriminatorValue("DEFAULTACCOUNT")
public class DefaultAccount extends UserId {

    private static final long serialVersionUID = 8137228294211060781L;

    @Column(name = "salutation", nullable = false)
    private String            salutation;

    @Column(name = "title", nullable = true)
    private String            title;

    @Column(name = "lastname", nullable = false, length = MAXLEN_NAME)
    private String lastName;

    @Column(name = "firstname", nullable = true, length = MAXLEN_NAME)
    private String firstName;

    @Column(name = "password", nullable = false)
    private String passwordDigest;

    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    @Embedded
    private PostAdress adresse;

    DefaultAccount() {}

    public DefaultAccount(final DefaultAccountDTO user) {
        this(user, UserRole.USER);
    }

    public DefaultAccount(final DefaultAccountDTO user, final UserRole role) {
        super(user.getEmail(), role, RegistrationTyp.DEFAULTACCOUNT);
        salutation = user.getSalutation();
        title = user.getTitle();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        passwordDigest = new BCryptPasswordEncoder().encode(RandomStringUtils.random(10));
        phoneNumber = user.getPhoneNumber();
        final Address adress = user.getAddress();
        adresse = new PostAdress(adress.getStreet(), adress.getZipcode(), adress.getCity(), adress.getHouseNr());
    }

    public String getSalutation() {
        return salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PostAdress getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "DefaultAccount ["
                + (salutation != null ? "salutation=" + salutation + ", " : "")
                + (title != null ? "title=" + title + ", " : "")
                + (lastName != null ? "lastName=" + lastName + ", " : "")
                + (firstName != null ? "firstName=" + firstName + ", " : "")
                + (passwordDigest != null ? "passwordDigest=" + passwordDigest + ", " : "")
                + (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
                + (adresse != null ? "adresse=" + adresse : "")
                + "]";
    }


}
