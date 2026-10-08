package com.campus.inventory_service.database.model;

import com.campus.inventory_service.database.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "movimentacao_estoque")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MovimentacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_movimentacao")
    private Long idMovimentacao;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false, length = 50)
    private TipoMovimentacao tipoMovimentacao;
}
