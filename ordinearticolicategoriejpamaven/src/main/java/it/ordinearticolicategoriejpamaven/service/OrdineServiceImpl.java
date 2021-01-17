package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.dao.OrdineDAO;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;

	@Override
	public List<Ordine> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			ordineDAO.update(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			ordineDAO.insert(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void rimuovi(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			ordineInstance = entityManager.merge(ordineInstance);

			for (Articolo articoliItem : ordineInstance.getArticoli()) {
				articoliItem.setOrdine(null);
				ordineInstance.getArticoli().remove(articoliItem);
			}

			ordineDAO.delete(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Ordine> cercaTuttiGliOrdiniTramiteArticoli(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findAllByArticolo(articoloInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {
		this.ordineDAO = ordineDAO;

	}

	@Override
	public List<Ordine> cercaTuttiGliOrdiniEffettuatiDaUnaCategoria(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findAllByCategoria(categoriaInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public List<Ordine> trovaTuttiGliOrdiniDoveNomeDestinatario(String nomeDestinatario) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findAllOrdiniByNomeDestinatario(nomeDestinatario);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Ordine> trovaTuttiGliOrdiniTramiteDescrzioneCategoria(String descrizioneCategoria) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findOrdineWhereDescrizioneCategoriaIs(descrizioneCategoria);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Ordine> trovaTuttiGliOrdiniDoveAmmontarePrezzoDiUnArticoloMaggioreDi(int prezzoInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findAllWhereAmountOneArticlePriceGreatherThan(prezzoInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
