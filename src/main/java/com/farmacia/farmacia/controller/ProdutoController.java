package com.farmacia.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.farmacia.farmacia.model.Produto;
import com.farmacia.farmacia.repository.RepositoryCategoria;
import com.farmacia.farmacia.repository.RepositoryProduto;

@RestController
@RequestMapping("/produto")
@CrossOrigin (origins ="*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private RepositoryProduto repositoryProduto;
	
	@Autowired
	private RepositoryCategoria repositoryCategoria;
	
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
	
	@PostMapping
	public ResponseEntity<Produto>post(@Valid @RequestBody Produto produto){
	if(repositoryCategoria.existsById(produto.getCategoria().getId()))
	
		return ResponseEntity.status(HttpStatus.CREATED)
	.body(repositoryProduto.save(produto));
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity<Produto>put(@Valid @RequestBody Produto produto){

	if(repositoryProduto.existsById(produto.getId())){

	if(repositoryCategoria.existsById(produto.getCategoria().getId()))

	return ResponseEntity.status(HttpStatus.OK)
	.body(repositoryProduto.save(produto));

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
			@DeleteMapping("/{id}")
			public void delete(@PathVariable Long id){
			Optional<Produto> categoria = repositoryProduto.findById(id);

			if(categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

			repositoryProduto.deleteById(id);
			} 
}
	
	
