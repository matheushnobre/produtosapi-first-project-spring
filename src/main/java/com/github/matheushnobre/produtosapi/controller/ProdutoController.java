package com.github.matheushnobre.produtosapi.controller;

import com.github.matheushnobre.produtosapi.model.Produto;
import com.github.matheushnobre.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id) {
        // Busca pelo ID e caso encontre, retorna o produto. Se não, retorna null.
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id") String id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscarPorNome(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }

}
