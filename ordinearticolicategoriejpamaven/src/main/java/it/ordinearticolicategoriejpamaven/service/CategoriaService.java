package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import it.ordinearticolicategoriejpamaven.dao.CategoriaDAO;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public interface CategoriaService {

	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categoria categorieInstance) throws Exception;

	public void inserisciNuovo(Categoria categorieInstance) throws Exception;

	public void rimuovi(Categoria categorieInstance) throws Exception;

	public void setCategorieDAO(CategoriaDAO categorieDAO);

}
