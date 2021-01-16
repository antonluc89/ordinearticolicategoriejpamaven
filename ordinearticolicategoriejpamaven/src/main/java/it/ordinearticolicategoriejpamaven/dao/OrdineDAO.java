package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import it.ordinearticolicategoriejpamaven.model.Articoli;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public List<Ordine> findAllByArticoli(Articoli articoliInput);

}
