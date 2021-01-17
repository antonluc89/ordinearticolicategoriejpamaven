package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import it.ordinearticolicategoriejpamaven.dao.OrdineDAO;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;

	public List<Ordine> cercaTuttiGliOrdiniTramiteArticoli(Articolo articoloInstance) throws Exception;
	
	public List<Ordine> cercaTuttiGliOrdiniEffettuatiDaUnaCategoria(Categoria categoriaInstance) throws Exception;
	
	public List<Ordine> trovaTuttiGliOrdiniDoveNomeDestinatario(String nomeDestinatario) throws Exception;
	
	public List<Ordine> trovaTuttiGliOrdiniTramiteDescrzioneCategoria(String descrizioneCategoria) throws Exception;
	
	public List<Ordine> trovaTuttiGliOrdiniDoveAmmontarePrezzoDiUnArticoloMaggioreDi(int prezzoInput) throws Exception;

	public void setOrdineDAO(OrdineDAO ordineDAO);

}
