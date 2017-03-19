package de.goldmann.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.tienda.domain.GoogleAccount;

public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, String> {
    GoogleAccount findByEmail(String email);
}
