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

    //visualizar produto
    public ProdutoResponseDto getProduto(Long idProduto){
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        EstoqueEntity estoque = estoqueRepository.findByProdutoIdProduto(idProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));


        ProdutoResponseDto responseDto = ProdutoResponseDto.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .quantidade(estoque.getQuantidade())
                .ativo(produto.getAtivo())
                .categoria(produto.getCategoria())
                .build();

        return responseDto;
    }

    //adicionar produto
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

    //atualiza registros do produto
    @Transactional
    public ProdutoResponseDto updateProduto(Long idProduto, ProdutoRequestDto requestDto){

        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        EstoqueEntity estoque = estoqueRepository.findByProdutoIdProduto(idProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        produto.setNome(requestDto.getNome());
        produto.setDescricao(requestDto.getDescricao());
        produto.setPreco(requestDto.getPreco());
        produto.setAtivo(requestDto.getAtivo());
        produto.setCategoria(requestDto.getCategoria());

        produtoRepository.save(produto);

        if(requestDto.getQuantidade() > estoque.getQuantidade()){
            int qtdMovimentacao = requestDto.getQuantidade() - estoque.getQuantidade();
            estoque.setQuantidade(requestDto.getQuantidade());

            MovimentacaoEntity movimentacao = MovimentacaoEntity.builder()
                    .produto(produto)
                    .quantidade(qtdMovimentacao)
                    .tipoMovimentacao(TipoMovimentacao.ENTRADA)
                    .build();

            movimentacaoRepository.save(movimentacao);

        } else if(requestDto.getQuantidade() < estoque.getQuantidade()){
            int qtdMovimentacao = estoque.getQuantidade() - requestDto.getQuantidade();
            estoque.setQuantidade(requestDto.getQuantidade());

            MovimentacaoEntity movimentacao = MovimentacaoEntity.builder()
                    .produto(produto)
                    .quantidade(qtdMovimentacao)
                    .tipoMovimentacao(TipoMovimentacao.SAIDA)
                    .build();

            movimentacaoRepository.save(movimentacao);
        }

        return ProdutoResponseDto.builder()
                .nome(requestDto.getNome())
                .descricao(requestDto.getDescricao())
                .preco(requestDto.getPreco())
                .quantidade(requestDto.getQuantidade())
                .ativo(requestDto.getAtivo())
                .categoria(requestDto.getCategoria())
                .build();
    }

    //deleta permanentemente um produto
    public void permanentDeleteProduto(Long idProduto){
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        produtoRepository.delete(produto);
    }


}
