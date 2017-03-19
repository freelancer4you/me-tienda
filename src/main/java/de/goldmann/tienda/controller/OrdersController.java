package de.goldmann.tienda.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.goldmann.tienda.memory.db.InMemoryDB;

@RestController
public class OrdersController {

    @Autowired
    private InMemoryDB db;

    // TOOD diese Resources ist noch ungeschützt
    @RequestMapping(value = "/api/resources/orders", method = RequestMethod.POST)
    public void addOrder(@RequestBody final String order) throws Exception {
        System.out.println(order);
        this.db.addOrder(order);
    }

    public List<String> getOrders() {
        return Collections.unmodifiableList(this.db.getOrders());
    }
}
