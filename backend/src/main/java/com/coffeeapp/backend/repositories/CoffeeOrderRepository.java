package com.coffeeapp.backend.repositories;

import com.coffeeapp.backend.entities.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
    // Το JpaRepository μας δίνει έτοιμες μεθόδους όπως save(), findAll(), findById() κτλ.
    // Το <CoffeeOrder, Long> σημαίνει: "Αυτό το repository διαχειρίζεται τον πίνακα CoffeeOrder και το Primary Key του (id) είναι τύπου Long".
}