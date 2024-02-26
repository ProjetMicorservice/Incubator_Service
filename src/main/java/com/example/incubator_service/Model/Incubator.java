package com.example.incubator_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Incubator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;

    private int maxCapacity;
    @OneToMany(mappedBy = "incubator")
    private List<Egg> eggs;

    // If you want to store the list of eggs currently in the incubator
    // You can choose an appropriate type for eggs
    // In this example, a list of type Egg is used (which you will need to define).
    // Make sure to adjust this based on your data model.
    // private List<Egg> eggs;

    // Other attributes or methods relevant to your application

    // Getters and Setters

    // Other methods
}
