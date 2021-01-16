package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import it.ordinearticolicategoriejpamaven.model.Articoli;
import it.ordinearticolicategoriejpamaven.model.Categorie;

public interface ArticoliDAO extends IBaseDAO<Articoli> {

	public List<Articoli> findAllByCategorie(Categorie categorieInput);

}
