package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import it.ordinearticolicategoriejpamaven.model.Categoria;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
	
	public List<Categoria> findAllCategoriaByArticoloInReleatedOrder(Ordine ordineInput);

}
