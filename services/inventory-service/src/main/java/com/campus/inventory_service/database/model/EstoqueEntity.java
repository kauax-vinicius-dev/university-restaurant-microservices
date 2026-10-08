package com.campus.inventory_service.database.model;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "estoque")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_produto", nullable = false, unique = true)
    private ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

}
