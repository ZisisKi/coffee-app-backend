package com.coffeeapp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // "Πιάνει" τα λάθη από ΟΛΟΥΣ τους Controllers
public class GlobalExceptionHandler {

    // 1. Διαχείριση λαθών από το DTO (π.χ. κενό όνομα)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Μαζεύουμε όλα τα μηνύματα από τα @NotBlank, @Min, @Max που βάλαμε στο DTO
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // Γυρνάμε ένα όμορφο JSON (π.χ. {"name": "Το όνομα δεν μπορεί να είναι κενό"}) με κωδικό 400
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 2. Διαχείριση για ΟΛΑ τα υπόλοιπα απρόβλεπτα λάθη (π.χ. έπεσε η βάση δεδομένων)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();

        // Το μήνυμα που θα δει ο χρήστης στο κινητό του (γενικό και ασφαλές)
        response.put("error", "Κάτι πήγε στραβά με τον διακομιστή. Παρακαλώ δοκιμάστε αργότερα.");

        // Το πραγματικό λάθος το τυπώνουμε ΜΟΝΟ στην κονσόλα μας για να ξέρουμε τι φταίει εμείς
        System.out.println("CRITICAL SERVER ERROR: " + ex.getMessage());

        // Κωδικός 500: Internal Server Error
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}