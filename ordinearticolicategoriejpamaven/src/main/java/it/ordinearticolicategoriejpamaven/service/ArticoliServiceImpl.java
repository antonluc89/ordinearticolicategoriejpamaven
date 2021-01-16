package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.dao.ArticoliDAO;
import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articoli;
import it.ordinearticolicategoriejpamaven.model.Categorie;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public class ArticoliServiceImpl implements ArticoliService {

	private ArticoliDAO articoliDAO;

	private OrdineService ordineService = MyServiceFactory.getOrdineServiceInstance();

	@Override
	public List<Articoli> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoliDAO.setEntityManager(entityManager);

			return articoliDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Articoli caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoliDAO.setEntityManager(entityManager);

			return articoliDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Articoli articoliInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoliDAO.setEntityManager(entityManager);

			articoliDAO.update(articoliInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void inserisciNuovo(Articoli articoliInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoliDAO.setEntityManager(entityManager);

			articoliDAO.insert(articoliInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void rimuovi(Articoli articoliInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			articoliDAO.setEntityManager(entityManager);

			List<Ordine> listaOrdineNeldB = ordineService.cercaTuttiGliOrdiniTramiteArticoli(articoliInstance);
			if (!listaOrdineNeldB.isEmpty())
				for (Ordine ordineItem : listaOrdineNeldB) {
					ordineItem.setArticoli(null);
					;

					ordineService.aggiorna(ordineItem);
				}
			articoliDAO.delete(articoliInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Articoli> cercaTuttiGliArticoliTramiteCategorie(Categorie categorieInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoliDAO.setEntityManager(entityManager);

			return articoliDAO.findAllByCategorie(categorieInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiungiCategorie(Articoli articoliInstance, Categorie categorieInstance) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoliDAO.setEntityManager(entityManager);

			articoliInstance = entityManager.merge(articoliInstance);

			categorieInstance = entityManager.merge(categorieInstance);

			articoliInstance.getCategorie().add(categorieInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void setArticoliDAO(ArticoliDAO articoliDAO) {
		this.articoliDAO = articoliDAO;

	}

}
