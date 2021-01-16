package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.dao.CategoriaDAO;
import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDAO;

	private ArticoloService articoloService = MyServiceFactory.getArticoloServiceInstance();

	@Override
	public List<Categoria> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Categoria categorieInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categoriaDAO.setEntityManager(entityManager);

			categoriaDAO.update(categorieInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categoriaDAO.setEntityManager(entityManager);

			categoriaDAO.insert(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categoriaDAO.setEntityManager(entityManager);

			List<Articolo> listaArticoliNeldB = articoloService
					.cercaTuttiGliArticoliTramiteCategorie(categoriaInstance);
			if (!listaArticoliNeldB.isEmpty())
				for (Articolo articoloItem : listaArticoliNeldB) {
					articoloItem.setCategorie(null);

					articoloService.aggiorna(articoloItem);
				}
			categoriaDAO.delete(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;

	}

}
