package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import it.ordinearticolicategoriejpamaven.dao.ArticoloDAO;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public interface ArticoloService {
	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;

	public List<Articolo> cercaTuttiGliArticoliTramiteCategorie(Categoria categoriaInstance) throws Exception;

	public void aggiungiCategorie(Articolo articoloInstance, Categoria categoriaInstance) throws Exception;
	
	public Long cercaTuttiGliArticoliDiUnaCategoriaECalcolaLaSomma(Categoria categoriaInstance) throws Exception;

	public void setArticoloDAO(ArticoloDAO articoloDAO);

}
