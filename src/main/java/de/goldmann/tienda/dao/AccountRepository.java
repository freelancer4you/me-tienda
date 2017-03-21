package de.goldmann.tienda.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.tienda.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
}
