package it.ordinearticolicategoriejpamaven.test;

import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articoli;
import it.ordinearticolicategoriejpamaven.model.Categorie;
import it.ordinearticolicategoriejpamaven.model.Ordine;
import it.ordinearticolicategoriejpamaven.service.ArticoliService;
import it.ordinearticolicategoriejpamaven.service.CategorieService;
import it.ordinearticolicategoriejpamaven.service.MyServiceFactory;
import it.ordinearticolicategoriejpamaven.service.OrdineService;

public class OrdineTest {
	public static void main(String[] args) {
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoliService articoliServiceInstance = MyServiceFactory.getArticoliServiceInstance();
		CategorieService categorieServiceInstance = MyServiceFactory.getCategorieServiceInstance();

		try {

			// TEST METODI CRUD PER ORDINE
			System.out.println("*********** PROVA INSERISCI ORDINE ***********");
			Ordine ordineNuovo = new Ordine("mario", "napoli");
			ordineServiceInstance.inserisciNuovo(ordineNuovo);

			System.out.println("*********** PROVA RIMOZIONE ORDINE ***********");
			Long idOrdineDaRimuovere = 3L;
			Ordine ordineDaRimuovere = ordineServiceInstance.caricaSingoloElemento(idOrdineDaRimuovere);
			if (idOrdineDaRimuovere != null) {
				System.out.println("Ordine da rimuovere caricato con successo: " + ordineDaRimuovere);
				ordineServiceInstance.rimuovi(ordineDaRimuovere);
			}

			System.out.println("*********** PROVA LIST ORDINE ***********");
			for (Ordine ordineItem : ordineServiceInstance.listAll()) {
				System.out.println(ordineItem);
				System.out.println("Il numero totale di ordini presenti nel DB ammonata a: "
						+ ordineServiceInstance.listAll().size());
			}

			System.out.println("*********** PROVA UPDATE ORDINE ***********");
			Long idOrdineDaAggiornare = 2L;
			Ordine ordineDaAggiornare = ordineServiceInstance.caricaSingoloElemento(idOrdineDaAggiornare);
			ordineDaAggiornare.setNomeDestinatario("Pierpaolo");
			ordineServiceInstance.aggiorna(ordineDaAggiornare);

			// TEST METODI CRUD PER ARTICOLI
			System.out.println("*********** PROVA INSERISCI ARTICOLO ***********");
			Long idOrdineACuiCollegare = 3L;
			Ordine ordineACuiCollegare = ordineServiceInstance.caricaSingoloElemento(idOrdineACuiCollegare);
			Articoli articoliNuovo = new Articoli("cappa", 100);
			articoliNuovo.setOrdine(ordineACuiCollegare);
			articoliServiceInstance.inserisciNuovo(articoliNuovo);

			System.out.println("*********** PROVA RIMOZIONE ARTICOLI ***********");
			Long idArticoloDaRimuovere = 2L;
			Articoli articoloDaRimuovere = articoliServiceInstance.caricaSingoloElemento(idArticoloDaRimuovere);
			if (idArticoloDaRimuovere != null) {
				System.out.println("Articolo da rimuovere caricato con successo: " + articoloDaRimuovere);
				articoliServiceInstance.rimuovi(articoloDaRimuovere);
			}

			System.out.println("*********** PROVA LIST ARTICOLI ***********");
			for (Articoli articoliItem : articoliServiceInstance.listAll()) {
				System.out.println(articoliItem);
				System.out.println("Il numero totale di articoli presenti nel DB ammonata a: "
						+ articoliServiceInstance.listAll().size());
			}

			System.out.println("*********** PROVA UPDATE ARTICOLI ***********");
			Long idArticoloDaAggiornare = 1L;
			Articoli articoloDaAggiornare = articoliServiceInstance.caricaSingoloElemento(idArticoloDaAggiornare);
			articoloDaAggiornare.setPrezzoSingolo(125);
			articoliServiceInstance.aggiorna(articoloDaAggiornare);

			// TEST METODI CRUD PER CATEGORIE
			System.out.println("*********** PROVA INSERISCI CATEGORIA ***********");
			Categorie categoriaNuova = new Categorie("attrezzi cucina");
			categorieServiceInstance.inserisciNuovo(categoriaNuova);

			System.out.println("*********** PROVA RIMOZIONE CATEGORIA ***********");
			Long idCategoriaDaRimuovere = 6L;
			Categorie categoriaDaRimuovere = categorieServiceInstance.caricaSingoloElemento(idCategoriaDaRimuovere);
			if (idCategoriaDaRimuovere != null) {
				System.out.println("Categoria caricata con successo: " + categoriaDaRimuovere);
				categorieServiceInstance.rimuovi(categoriaDaRimuovere);
			}

			System.out.println("*********** PROVA LIST CATEGORIE ***********");
			for (Categorie categorieItem : categorieServiceInstance.listAll()) {
				System.out.println(categorieItem);
				System.out.println("Il numero totale di categorie presenti nel DB ammonata a: "
						+ categorieServiceInstance.listAll().size());
			}

			System.out.println("*********** PROVA UPDATE CATEGORIE ***********");
			Long idCategoriaDaAggiornare = 3L;
			Categorie categoriaDaAggiornare = categorieServiceInstance.caricaSingoloElemento(idCategoriaDaAggiornare);
			categoriaDaAggiornare.setDescrizione("hobby");
			categorieServiceInstance.aggiorna(categoriaDaAggiornare);

			// TEST DUE METODI FINDALLBY
			System.out.println("*********** PROVA TROVA GLI ORDINI BY ARTICOLI ***********");
			Long idOrdinePerRicerca = 3L;
			Articoli articoloPerRicerca = articoliServiceInstance.caricaSingoloElemento(idOrdinePerRicerca);
			for (Ordine ordineItem : ordineServiceInstance.cercaTuttiGliOrdiniTramiteArticoli(articoloPerRicerca)) {
				System.out.println(ordineItem);
			}

			System.out.println("*********** PROVA TROVA GLI ARTICOLI BY CATEGORIE ***********");
			Long idCategoriaPerRicerca = 4L;
			Categorie categoriaPerRicerca = categorieServiceInstance.caricaSingoloElemento(idCategoriaPerRicerca);
			for (Articoli articoloItem : articoliServiceInstance
					.cercaTuttiGliArticoliTramiteCategorie(categoriaPerRicerca)) {
				System.out.println(articoloItem);
			}

			System.out.println("*********** PROVA COLLEGO ARTICOLI A CATEGORIA ***********");
			Articoli articoloDaCollegare = articoliServiceInstance.caricaSingoloElemento(3L);
			Categorie categoriaDaInserireECollegare = new Categorie("posate in alluminio");
			categorieServiceInstance.inserisciNuovo(categoriaDaInserireECollegare);
			if (articoloDaCollegare != null) {
				articoliServiceInstance.aggiungiCategorie(articoloDaCollegare, categoriaDaInserireECollegare);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			EntityManagerUtil.shutdown();
		}

	}
}
