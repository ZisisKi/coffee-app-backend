package com.coffeeapp.backend.services;

import com.coffeeapp.backend.dto.CoffeeOrderDTO;
import com.coffeeapp.backend.entities.CoffeeOrder;
import com.coffeeapp.backend.repositories.CoffeeOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoffeeOrderService {

    private final CoffeeOrderRepository repository;

    // 1. ΔΗΜΙΟΥΡΓΙΑ (Αυτό που είχαμε ήδη)
    public CoffeeOrder createOrder(CoffeeOrderDTO dto) {
        CoffeeOrder order = new CoffeeOrder();
        order.setName(dto.getName());
        order.setAddress(dto.getAddress());
        order.setType(dto.getType());
        order.setSelectedDrink(dto.getSelectedDrink());
        order.setHasSugar(dto.isHasSugar());
        order.setHasSyrup(dto.isHasSyrup());
        order.setDoubleShot(dto.isDoubleShot());
        order.setSweetnessLevel(dto.getSweetnessLevel());
        return repository.save(order);
    }

    // 2. ΑΝΑΖΗΤΗΣΗ ΟΛΩΝ
    public List<CoffeeOrder> getAllOrders() {
        return repository.findAll();
    }

    // 3. ΑΝΑΖΗΤΗΣΗ ΒΑΣΕΙ ID (π.χ. φέρε μου την παραγγελία #5)
    public Optional<CoffeeOrder> getOrderById(Long id) {
        return repository.findById(id);
    }

    // 4. ΔΙΑΓΡΑΦΗ ΒΑΣΕΙ ID
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}