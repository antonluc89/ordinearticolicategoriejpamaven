package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import it.ordinearticolicategoriejpamaven.dao.CategoriaDAO;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public interface CategoriaService {

	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Categoria categoriaInstance) throws Exception;

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

}
