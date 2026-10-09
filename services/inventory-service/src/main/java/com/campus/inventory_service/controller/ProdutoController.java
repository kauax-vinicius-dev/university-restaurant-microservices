package com.campus.inventory_service.controller;


import com.campus.inventory_service.dto.ProdutoRequestDto;
import com.campus.inventory_service.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Validated
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduto(@Valid @RequestBody ProdutoRequestDto requestDto){
        produtoService.addProduto(requestDto);
    }
}
