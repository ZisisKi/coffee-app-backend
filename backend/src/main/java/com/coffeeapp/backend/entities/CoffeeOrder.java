package com.coffeeapp.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // Το όνομα του πίνακα στη MySQL
@Data // Το Lombok φτιάχνει αυτόματα Getters/Setters!
public class CoffeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Το μοναδικό ID της παραγγελίας που αυξάνεται αυτόματα

    private String name;
    private String address;
    private String type;
    private String selectedDrink;
    private boolean hasSugar;
    private boolean hasSyrup;
    private boolean isDoubleShot;
    private double sweetnessLevel;

    private LocalDateTime createdAt; // Ημερομηνία και ώρα παραγγελίας

    // Αυτό τρέχει αυτόματα μόλις πάει να σωθεί η εγγραφή στη βάση
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}