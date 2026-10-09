package com.campus.inventory_service.service;

import com.campus.inventory_service.database.enums.TipoMovimentacao;
import com.campus.inventory_service.database.model.EstoqueEntity;
import com.campus.inventory_service.database.model.MovimentacaoEntity;
import com.campus.inventory_service.database.model.ProdutoEntity;
import com.campus.inventory_service.database.repository.IEstoqueRepository;
import com.campus.inventory_service.database.repository.IMovimentacaoRepository;
import com.campus.inventory_service.database.repository.IProdutoRepository;
import com.campus.inventory_service.dto.ProdutoRequestDto;
import com.campus.inventory_service.dto.ProdutoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final IProdutoRepository produtoRepository;
    private final IEstoqueRepository estoqueRepository;
    private final IMovimentacaoRepository movimentacaoRepository;

    public ProdutoResponseDto getProduto(Long idProduto){
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        EstoqueEntity estoque = estoqueRepository.findById(idProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        String ativo = "";
        if(produto.getAtivo()) {
            ativo = "Disponível";
        }else{
            ativo = "Indisponível";
        }
        ProdutoResponseDto responseDto = ProdutoResponseDto.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .quantidade(estoque.getQuantidade())
                .ativo(ativo)
                .categoria(produto.getCategoria())
                .build();

        return responseDto;
    }

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
