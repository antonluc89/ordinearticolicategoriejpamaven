package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.dao.CategoriaDAO;
import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categorieDAO;

	private ArticoloService articoliService = MyServiceFactory.getArticoliServiceInstance();

	@Override
	public List<Categoria> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categorieDAO.setEntityManager(entityManager);

			return categorieDAO.list();
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
			categorieDAO.setEntityManager(entityManager);

			return categorieDAO.get(id);

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

			categorieDAO.setEntityManager(entityManager);

			categorieDAO.update(categorieInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Categoria categorieInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categorieDAO.setEntityManager(entityManager);

			categorieDAO.insert(categorieInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Categoria categorieInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categorieDAO.setEntityManager(entityManager);

			List<Articolo> listaArticoliNeldB = articoliService
					.cercaTuttiGliArticoliTramiteCategorie(categorieInstance);
			if (!listaArticoliNeldB.isEmpty())
				for (Articolo articoliItem : listaArticoliNeldB) {
					articoliItem.setCategorie(null);

					articoliService.aggiorna(articoliItem);
				}
			categorieDAO.delete(categorieInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void setCategorieDAO(CategoriaDAO categorieDAO) {
		this.categorieDAO = categorieDAO;

	}

}
