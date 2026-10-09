package com.campus.inventory_service.database.repository;

import com.campus.inventory_service.database.model.MovimentacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Long> {
}
