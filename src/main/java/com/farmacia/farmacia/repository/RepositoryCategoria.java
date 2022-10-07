package com.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.model.Categoria;

@Repository
public interface RepositoryCategoria extends JpaRepository <Categoria,Long>{
	public List <Categoria> findAllByDescricaoContainingIgnoreCase(@Param("descrição") String descricao);


}	
