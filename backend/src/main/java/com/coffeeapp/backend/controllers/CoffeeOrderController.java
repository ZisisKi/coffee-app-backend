package com.coffeeapp.backend.controllers;

import com.coffeeapp.backend.dto.CoffeeOrderDTO;
import com.coffeeapp.backend.entities.CoffeeOrder;
import com.coffeeapp.backend.services.CoffeeOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Λέει στο Spring Boot ότι αυτή η κλάση είναι REST API
@RequestMapping("/api/orders") // Όλα τα URL θα ξεκινάνε από το /api/orders
@RequiredArgsConstructor
public class CoffeeOrderController {

    private final CoffeeOrderService service;

    // 1. ΑΠΟΘΗΚΕΥΣΗ (POST request)
    @PostMapping
    public ResponseEntity<CoffeeOrder> createOrder(@Valid @RequestBody CoffeeOrderDTO dto) {
        // Το @Valid ελέγχει τους κανόνες που βάλαμε στο DTO (π.χ. να μην είναι κενό το όνομα)
        CoffeeOrder savedOrder = service.createOrder(dto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // 2. ΕΜΦΑΝΙΣΗ ΟΛΩΝ (GET request)
    @GetMapping
    public ResponseEntity<List<CoffeeOrder>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    // 3. ΕΜΦΑΝΙΣΗ ΜΙΑΣ ΠΑΡΑΓΓΕΛΙΑΣ (GET request στο /api/orders/{id})
    @GetMapping("/{id}")
    public ResponseEntity<CoffeeOrder> getOrderById(@PathVariable Long id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Αν δεν βρει το ID, γυρνάει 404 Not Found
    }

    // 4. ΔΙΑΓΡΑΦΗ (DELETE request στο /api/orders/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build(); // 204 No Content σημαίνει "Διαγράφηκε επιτυχώς"
    }
}
