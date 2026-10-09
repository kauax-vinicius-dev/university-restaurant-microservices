package com.campus.inventory_service.dto;


import com.campus.inventory_service.database.enums.CategoriaEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoResponseDto {

    private Long id;

    private String nome;

    private String descricao;

    private Integer quantidade;

    private String ativo;

    private CategoriaEnum categoria;
}
