package com.campus.inventory_service.dto;


import com.campus.inventory_service.database.enums.CategoriaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoRequestDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Boolean ativo;

    @NotNull
    private CategoriaEnum categoria;
}
