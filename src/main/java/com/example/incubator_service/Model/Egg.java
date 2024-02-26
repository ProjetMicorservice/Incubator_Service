package com.example.incubator_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Egg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;

    // Type of egg, status, remaining time, etc.
    private String type;
    private String status;
    private Long remainingTime;

    @ManyToOne
    private Incubator incubator;
}
