package de.goldmann.tienda.controller;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.tienda.dao.AccountRepository;
import de.goldmann.tienda.dao.OrderRepository;
import de.goldmann.tienda.domain.Account;
import de.goldmann.tienda.domain.Order;
import de.goldmann.tienda.dto.OrderDTO;
import de.goldmann.tienda.memory.db.InMemoryDB;

@RestController
public class OrdersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final InMemoryDB db;

    private final OrderRepository orderRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public OrdersController(final InMemoryDB db, final OrderRepository orderRepository,
            final AccountRepository accountRepository) {
        this.db = Objects.requireNonNull(db, "db");
        this.orderRepository = Objects.requireNonNull(orderRepository, "orderRepository");
        this.accountRepository = Objects.requireNonNull(accountRepository, "accountRepository");
    }

    @RequestMapping(value = "/api/resources/orders", method = RequestMethod.POST)
    public void addOrder(@RequestBody final String orderStr) throws Exception {
        System.out.println(orderStr);

        final ObjectMapper mapper = new ObjectMapper();
        try
        {
            final OrderDTO orderDto = mapper.readValue(orderStr, OrderDTO.class);
            final Optional<Account> acc = accountRepository.findByEmail(orderDto.getClient());
            orderRepository.save(new Order(acc.get(), orderDto.getDate(), orderDto.getCart()));
        }
        catch (final Exception e)
        {
            LOGGER.error("Orderverarbeitung:" + orderStr, e);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        db.addOrder(orderStr);
    }

}
