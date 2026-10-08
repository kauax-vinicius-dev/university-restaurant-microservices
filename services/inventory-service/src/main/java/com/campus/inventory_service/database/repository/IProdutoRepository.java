package com.campus.inventory_service.database.repository;

import com.campus.inventory_service.database.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
