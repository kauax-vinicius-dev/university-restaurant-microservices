package com.campus.inventory_service.database.repository;

import com.campus.inventory_service.database.model.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEstoqueRepository extends JpaRepository<EstoqueEntity, Long> {

    Optional<EstoqueEntity> findByProdutoId(Long idProduto);
}
