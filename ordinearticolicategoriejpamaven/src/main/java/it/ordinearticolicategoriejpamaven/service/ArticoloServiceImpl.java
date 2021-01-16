package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.dao.ArticoloDAO;
import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoloDAO;

	private OrdineService ordineService = MyServiceFactory.getOrdineServiceInstance();

	@Override
	public List<Articolo> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoloDAO.setEntityManager(entityManager);

			return articoloDAO.list();

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
			articoloDAO.setEntityManager(entityManager);

			return articoloDAO.get(id);

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

			articoloDAO.setEntityManager(entityManager);

			articoloDAO.update(articoliInstance);

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

			articoloDAO.setEntityManager(entityManager);

			articoloDAO.insert(articoliInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void rimuovi(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			articoloDAO.setEntityManager(entityManager);

			List<Ordine> listaOrdineNeldB = ordineService.cercaTuttiGliOrdiniTramiteArticoli(articoloInstance);
			if (!listaOrdineNeldB.isEmpty())
				for (Ordine ordineItem : listaOrdineNeldB) {
					ordineItem.setArticoli(null);
					;

					ordineService.aggiorna(ordineItem);
				}
			articoloDAO.delete(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Articolo> cercaTuttiGliArticoliTramiteCategorie(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoloDAO.setEntityManager(entityManager);

			return articoloDAO.findAllByCategoria(categoriaInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiungiCategorie(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoloDAO.setEntityManager(entityManager);

			articoloInstance = entityManager.merge(articoloInstance);

			categoriaInstance = entityManager.merge(categoriaInstance);

			articoloInstance.getCategorie().add(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAO) {
		this.articoloDAO = articoloDAO;

	}

}
