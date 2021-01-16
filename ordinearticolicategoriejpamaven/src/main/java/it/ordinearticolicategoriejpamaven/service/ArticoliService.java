package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import it.ordinearticolicategoriejpamaven.dao.ArticoliDAO;
import it.ordinearticolicategoriejpamaven.model.Articoli;
import it.ordinearticolicategoriejpamaven.model.Categorie;

public interface ArticoliService {
	public List<Articoli> listAll() throws Exception;

	public Articoli caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articoli articoliInstance) throws Exception;

	public void inserisciNuovo(Articoli articoliInstance) throws Exception;

	public void rimuovi(Articoli articoliInstance) throws Exception;

	public List<Articoli> cercaTuttiGliArticoliTramiteCategorie(Categorie categorieInstance) throws Exception;

	public void aggiungiCategorie(Articoli articoliInstance, Categorie categorieInstance) throws Exception;

	public void setArticoliDAO(ArticoliDAO articoliDAO);

}
