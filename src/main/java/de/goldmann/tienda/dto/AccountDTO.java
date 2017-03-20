package de.goldmann.tienda.dto;

import java.io.Serializable;


public class AccountDTO implements Serializable {

    private static final long serialVersionUID = -3682829511872578712L;

    private String            familyName;
    private String            gender;
    private String            givenName;
    private String            displayName;
    private String            email;
    private Address           adress;

    public AccountDTO() {}

    public AccountDTO(final String familyName, final String gender, final String givenName,
            final String displayName, final String email, final Address adress) {
        super();
        this.familyName = familyName;
        this.gender = gender;
        this.givenName = givenName;
        this.displayName = displayName;
        this.email = email;
        this.adress = adress;
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

    public String getEmail() {
        return email;
    }

    public Address getAdress() {
        return adress;
    }

}
