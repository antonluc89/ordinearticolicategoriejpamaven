package it.ordinearticolicategoriejpamaven.test;

import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;
import it.ordinearticolicategoriejpamaven.model.Ordine;
import it.ordinearticolicategoriejpamaven.service.ArticoloService;
import it.ordinearticolicategoriejpamaven.service.CategoriaService;
import it.ordinearticolicategoriejpamaven.service.MyServiceFactory;
import it.ordinearticolicategoriejpamaven.service.OrdineService;

public class OrdineTest {
	public static void main(String[] args) {
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

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
			Articolo articoliNuovo = new Articolo("cappa", 100);
			articoliNuovo.setOrdine(ordineACuiCollegare);
			articoloServiceInstance.inserisciNuovo(articoliNuovo);

			System.out.println("*********** PROVA RIMOZIONE ARTICOLO ***********");
			Long idArticoloDaRimuovere = 2L;
			Articolo articoloDaRimuovere = articoloServiceInstance.caricaSingoloElemento(idArticoloDaRimuovere);
			if (idArticoloDaRimuovere != null) {
				System.out.println("Articolo da rimuovere caricato con successo: " + articoloDaRimuovere);
				articoloServiceInstance.rimuovi(articoloDaRimuovere);
			}

			System.out.println("*********** PROVA LIST ARTICOLI ***********");
			for (Articolo articoliItem : articoloServiceInstance.listAll()) {
				System.out.println(articoliItem);
				System.out.println("Il numero totale di articoli presenti nel DB ammonata a: "
						+ articoloServiceInstance.listAll().size());
			}

			System.out.println("*********** PROVA UPDATE ARTICOLO ***********");
			Long idArticoloDaAggiornare = 1L;
			Articolo articoloDaAggiornare = articoloServiceInstance.caricaSingoloElemento(idArticoloDaAggiornare);
			articoloDaAggiornare.setPrezzoSingolo(125);
			articoloServiceInstance.aggiorna(articoloDaAggiornare);

			// TEST METODI CRUD PER CATEGORIE
			System.out.println("*********** PROVA INSERISCI CATEGORIA ***********");
			Categoria categoriaNuova = new Categoria("attrezzi cucina");
			categoriaServiceInstance.inserisciNuovo(categoriaNuova);

			System.out.println("*********** PROVA RIMOZIONE CATEGORIA ***********");
			Long idCategoriaDaRimuovere = 6L;
			Categoria categoriaDaRimuovere = categoriaServiceInstance.caricaSingoloElemento(idCategoriaDaRimuovere);
			if (idCategoriaDaRimuovere != null) {
				System.out.println("Categoria caricata con successo: " + categoriaDaRimuovere);
				categoriaServiceInstance.rimuovi(categoriaDaRimuovere);
			}

			System.out.println("*********** PROVA LIST CATEGORIA ***********");
			for (Categoria categorieItem : categoriaServiceInstance.listAll()) {
				System.out.println(categorieItem);
				System.out.println("Il numero totale di categorie presenti nel DB ammonata a: "
						+ categoriaServiceInstance.listAll().size());
			}

			System.out.println("*********** PROVA UPDATE CATEGORIA ***********");
			Long idCategoriaDaAggiornare = 3L;
			Categoria categoriaDaAggiornare = categoriaServiceInstance.caricaSingoloElemento(idCategoriaDaAggiornare);
			categoriaDaAggiornare.setDescrizione("hobby");
			categoriaServiceInstance.aggiorna(categoriaDaAggiornare);

			// TEST DUE METODI FINDALLBY
			System.out.println("*********** PROVA TROVA GLI ORDINI BY ARTICOLO ***********");
			Long idOrdinePerRicerca = 3L;
			Articolo articoloPerRicerca = articoloServiceInstance.caricaSingoloElemento(idOrdinePerRicerca);
			for (Ordine ordineItem : ordineServiceInstance.cercaTuttiGliOrdiniTramiteArticoli(articoloPerRicerca)) {
				System.out.println(ordineItem);
			}

			System.out.println("*********** PROVA TROVA GLI ARTICOLI BY CATEGORIA ***********");
			Long idCategoriaPerRicerca = 4L;
			Categoria categoriaPerRicerca = categoriaServiceInstance.caricaSingoloElemento(idCategoriaPerRicerca);
			for (Articolo articoloItem : articoloServiceInstance
					.cercaTuttiGliArticoliTramiteCategorie(categoriaPerRicerca)) {
				System.out.println(articoloItem);
			}

			System.out.println("*********** PROVA COLLEGO ARTICOLO A CATEGORIA ***********");
			Articolo articoloDaCollegare = articoloServiceInstance.caricaSingoloElemento(3L);
			Categoria categoriaDaInserireECollegare = new Categoria("posate in alluminio");
			categoriaServiceInstance.inserisciNuovo(categoriaDaInserireECollegare);
			if (articoloDaCollegare != null) {
				articoloServiceInstance.aggiungiCategorie(articoloDaCollegare, categoriaDaInserireECollegare);
			}
			
			System.out.println("*********** PROVA TROVA GLI ORDINI BY CATEGORIA ***********");
			Long idCategoriaRicercaOrdini = 4L;
			Categoria categoriRicercaOrdini = categoriaServiceInstance.caricaSingoloElemento(idCategoriaRicercaOrdini);
			for (Ordine ordineItem : ordineServiceInstance
					.cercaTuttiGliOrdiniEffettuatiDaUnaCategoria(categoriRicercaOrdini)) {
				System.out.println(ordineItem);
			}
			
			System.out.println("*********** PROVA TROVA LE CATEGORIE DEGLI ARTICOLI PRESENTI IN UN ORDINE ***********");
			Long idOrdinePerRicercaCategorie = 2L;
			Ordine ordinePerRicerca = ordineServiceInstance.caricaSingoloElemento(idOrdinePerRicercaCategorie);
			for (Categoria categoriaItem : categoriaServiceInstance
					.trovaTutteLeCategorieDegliArticoliDiUnDatoordine(ordinePerRicerca)) {
				System.out.println(categoriaItem);
			}
			
			System.out.println("*********** PROVA TROVA GLI ARTICOLI BY CATEGORIA E CALCOLA IL TOTALE ***********");
			Long idCategoriaPerTotalePrezzoArticoli = 4L;
			Categoria categoriaPerTotalePrezzoArticoli = categoriaServiceInstance.caricaSingoloElemento(idCategoriaPerTotalePrezzoArticoli);
			Long prezzoTotale=articoloServiceInstance.cercaTuttiGliArticoliDiUnaCategoriaECalcolaLaSomma(categoriaPerTotalePrezzoArticoli);
			System.out.println(prezzoTotale);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			EntityManagerUtil.shutdown();
		}

	}
}
