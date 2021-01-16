package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public List<Ordine> findAllByArticolo(Articolo articoloInput);

}
