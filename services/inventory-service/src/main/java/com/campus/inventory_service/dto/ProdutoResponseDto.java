package com.campus.inventory_service.dto;


import com.campus.inventory_service.database.enums.CategoriaEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoResponseDto {

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Integer quantidade;

    private Boolean ativo;

    private CategoriaEnum categoria;
}
