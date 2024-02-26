package com.example.incubator_service.Repository;

import com.example.incubator_service.Model.Incubator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncubatorRepository extends JpaRepository<Incubator,Long> {
    List<Incubator> findByOwnerId(Long ownerId);
}
