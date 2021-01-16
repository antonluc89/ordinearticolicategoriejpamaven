package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import it.ordinearticolicategoriejpamaven.dao.ArticoloDAO;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public interface ArticoloService {
	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articoliInstance) throws Exception;

	public void inserisciNuovo(Articolo articoliInstance) throws Exception;

	public void rimuovi(Articolo articoliInstance) throws Exception;

	public List<Articolo> cercaTuttiGliArticoliTramiteCategorie(Categoria categorieInstance) throws Exception;

	public void aggiungiCategorie(Articolo articoliInstance, Categoria categorieInstance) throws Exception;

	public void setArticoliDAO(ArticoloDAO articoliDAO);

}
