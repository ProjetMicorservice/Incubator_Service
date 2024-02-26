package com.example.incubator_service.Repository;

import com.example.incubator_service.Model.Egg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EggRepository extends JpaRepository<Egg,Long> {
    List<Egg> findByOwnerId(Long ownerId);
}