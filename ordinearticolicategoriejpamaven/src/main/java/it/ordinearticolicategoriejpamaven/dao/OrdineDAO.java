package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public List<Ordine> findAllByArticolo(Articolo articoloInput);
	
	public List<Ordine> findAllByCategoria(Categoria categoriaInput);
	
	public List<Ordine> findAllOrdiniByNomeDestinatario(String nameDestinatario);
	
	public List<Ordine> findOrdineWhereDescrizioneCategoriaIs(String categoryDescriptionInput);
	
	public List<Ordine> findAllWhereAmountOneArticlePriceGreatherThan(int priceInput);
	

}
