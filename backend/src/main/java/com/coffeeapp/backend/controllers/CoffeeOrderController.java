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

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class CoffeeOrderController {

    private final CoffeeOrderService service;

    @PostMapping
    public ResponseEntity<CoffeeOrder> createOrder(@Valid @RequestBody CoffeeOrderDTO dto) {
        CoffeeOrder savedOrder = service.createOrder(dto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CoffeeOrder>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeOrder> getOrderById(@PathVariable Long id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
