package com.campus.inventory_service.service;

import com.campus.inventory_service.database.model.ProdutoEntity;
import com.campus.inventory_service.database.repository.IProdutoRepository;
import com.campus.inventory_service.dto.ProdutoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final IProdutoRepository produtoRepository;
    public void addProduto(ProdutoRequestDto RequestDto){

        produtoRepository.save(ProdutoEntity.builder()
                .nome(RequestDto.getNome())
                .descricao(RequestDto.getDescricao())
                .preco(RequestDto.getPreco())
                .ativo(RequestDto.getAtivo())
                .categoria(RequestDto.getCategoria())
                .build()
        );


    }
}
