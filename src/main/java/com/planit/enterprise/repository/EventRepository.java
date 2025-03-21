package com.planit.enterprise.repository;
import com.planit.enterprise.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findById(int id);
    List<Event> findAll();
    Event findByName(String name);
}
