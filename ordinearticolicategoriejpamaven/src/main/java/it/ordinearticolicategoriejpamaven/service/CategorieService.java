package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import it.ordinearticolicategoriejpamaven.dao.CategorieDAO;
import it.ordinearticolicategoriejpamaven.model.Categorie;

public interface CategorieService {

	public List<Categorie> listAll() throws Exception;

	public Categorie caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categorie categorieInstance) throws Exception;

	public void inserisciNuovo(Categorie categorieInstance) throws Exception;

	public void rimuovi(Categorie categorieInstance) throws Exception;

	public void setCategorieDAO(CategorieDAO categorieDAO);

}
