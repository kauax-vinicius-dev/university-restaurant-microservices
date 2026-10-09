package com.campus.inventory_service.service;

import com.campus.inventory_service.database.enums.TipoMovimentacao;
import com.campus.inventory_service.database.model.EstoqueEntity;
import com.campus.inventory_service.database.model.MovimentacaoEntity;
import com.campus.inventory_service.database.model.ProdutoEntity;
import com.campus.inventory_service.database.repository.IEstoqueRepository;
import com.campus.inventory_service.database.repository.IMovimentacaoRepository;
import com.campus.inventory_service.database.repository.IProdutoRepository;
import com.campus.inventory_service.dto.ProdutoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final IProdutoRepository produtoRepository;
    private final IEstoqueRepository estoqueRepository;
    private final IMovimentacaoRepository movimentacaoRepository;

    @Transactional
    public void addProduto(ProdutoRequestDto requestDto) {

        //registra produto
        ProdutoEntity produto = ProdutoEntity.builder()
                .nome(requestDto.getNome())
                .descricao(requestDto.getDescricao())
                .preco(requestDto.getPreco())
                .ativo(requestDto.getAtivo())
                .categoria(requestDto.getCategoria())
                .build();

        produtoRepository.save(produto);

        //registra sua quantidade no estoque
        EstoqueEntity estoqueEntity = EstoqueEntity.builder()
                .produto(produto)
                .quantidade(requestDto.getQuantidade())
                .build();

        estoqueRepository.save(estoqueEntity);

        //registra movimentacao desse produto
        MovimentacaoEntity movimentacao = MovimentacaoEntity.builder()
                .produto(produto)
                .quantidade(requestDto.getQuantidade())
                .tipoMovimentacao(TipoMovimentacao.ENTRADA)
                .build();

        movimentacaoRepository.save(movimentacao);
    }
}
