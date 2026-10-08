package com.campus.inventory_service.database.model;


import com.campus.inventory_service.database.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "produto")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CategoriaEnum categoria;

}

