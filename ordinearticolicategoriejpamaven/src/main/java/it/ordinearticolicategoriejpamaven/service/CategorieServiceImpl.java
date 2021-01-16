package it.ordinearticolicategoriejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.dao.CategorieDAO;
import it.ordinearticolicategoriejpamaven.dao.EntityManagerUtil;
import it.ordinearticolicategoriejpamaven.model.Articoli;
import it.ordinearticolicategoriejpamaven.model.Categorie;

public class CategorieServiceImpl implements CategorieService {

	private CategorieDAO categorieDAO;

	private ArticoliService articoliService = MyServiceFactory.getArticoliServiceInstance();

	@Override
	public List<Categorie> listAll() throws Exception {
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
	public Categorie caricaSingoloElemento(Long id) throws Exception {
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
	public void aggiorna(Categorie categorieInstance) throws Exception {
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
	public void inserisciNuovo(Categorie categorieInstance) throws Exception {
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
	public void rimuovi(Categorie categorieInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categorieDAO.setEntityManager(entityManager);

			List<Articoli> listaArticoliNeldB = articoliService
					.cercaTuttiGliArticoliTramiteCategorie(categorieInstance);
			if (!listaArticoliNeldB.isEmpty())
				for (Articoli articoliItem : listaArticoliNeldB) {
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
	public void setCategorieDAO(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;

	}

}
