package de.goldmann.tienda.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import de.goldmann.tienda.dto.AccountDTO;

@Entity
@DiscriminatorValue("GOOGLEACCOUNT")
public class Account extends UserId {

    private static final long serialVersionUID = -247943674703330516L;

    @Column(name = "familyname", nullable = false, length = MAXLEN_NAME)
    private String            familyName;

    @Column(name = "givenname", nullable = true, length = MAXLEN_NAME)
    private String            givenName;

    @Column(name = "gender", nullable = false)
    private String            gender;

    @Column(name = "displayName", nullable = false)
    private String            displayName;

    @Embedded
    private PostAdress        adresse;

    Account() {
    }

    public Account(final AccountDTO acc) {
        this(acc, UserRole.USER);
    }

    public Account(final AccountDTO acc, final UserRole role) {
        super(acc.getEmail(), role, RegistrationTyp.GOOGLEACCOUNT);
        displayName = acc.getDisplayName();
        familyName = acc.getFamilyName();
        gender = acc.getGender();
        givenName = acc.getGivenName();
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGender() {
        return gender;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
