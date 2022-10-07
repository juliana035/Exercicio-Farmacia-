package com.farmacia.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmacia.model.Categoria;
import com.farmacia.farmacia.model.Produto;
import com.farmacia.farmacia.repository.RepositoryProduto;

@RestController
@RequestMapping("/produto")
@CrossOrigin (origins ="*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private RepositoryProduto repositoryProduto;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repositoryProduto.findAll());
	
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return repositoryProduto.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
		 .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}	
		
	@GetMapping("/nomeProd/{nomeProd}")
	public ResponseEntity<List< Produto >>getByDescricao(@PathVariable String nomeProd){
	return ResponseEntity.ok(repositoryProduto.findAllByNomeProdContainingIgnoreCase(nomeProd));
	
}
	@GetMapping("/preco{preco}")
	public ResponseEntity<List<Produto>>getByPreco(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(repositoryProduto.findAllByPreco(preco));
	}
	
}