package de.goldmann.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.tienda.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
