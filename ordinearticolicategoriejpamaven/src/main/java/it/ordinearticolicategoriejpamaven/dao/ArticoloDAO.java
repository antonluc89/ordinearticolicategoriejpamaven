package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	public List<Articolo> findAllByCategoria(Categoria categorieInput);

}
