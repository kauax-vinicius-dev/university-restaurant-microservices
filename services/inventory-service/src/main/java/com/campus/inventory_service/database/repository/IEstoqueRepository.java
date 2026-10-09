package com.campus.inventory_service.database.repository;

import com.campus.inventory_service.database.model.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstoqueRepository extends JpaRepository<EstoqueEntity, Long> {
}
