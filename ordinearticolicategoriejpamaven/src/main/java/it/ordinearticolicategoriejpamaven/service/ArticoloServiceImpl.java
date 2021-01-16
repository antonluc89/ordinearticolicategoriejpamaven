package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.dao.ArticoloDAO;
import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoliDAO;

	private OrdineService ordineService = MyServiceFactory.getOrdineServiceInstance();

	@Override
	public List<Articolo> listAll() throws Exception {
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
	public Articolo caricaSingoloElemento(Long id) throws Exception {
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
	public void aggiorna(Articolo articoliInstance) throws Exception {
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
	public void inserisciNuovo(Articolo articoliInstance) throws Exception {
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
	public void rimuovi(Articolo articoliInstance) throws Exception {
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
	public List<Articolo> cercaTuttiGliArticoliTramiteCategorie(Categoria categorieInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoliDAO.setEntityManager(entityManager);

			return articoliDAO.findAllByCategoria(categorieInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiungiCategorie(Articolo articoliInstance, Categoria categorieInstance) throws Exception {

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
	public void setArticoliDAO(ArticoloDAO articoliDAO) {
		this.articoliDAO = articoliDAO;

	}

}
