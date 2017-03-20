package de.goldmann.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.tienda.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
}
