package com.rackmanager.infrastructure.database.repository;

import com.rackmanager.infrastructure.database.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RackRepository extends JpaRepository<Rack, UUID> {
}
