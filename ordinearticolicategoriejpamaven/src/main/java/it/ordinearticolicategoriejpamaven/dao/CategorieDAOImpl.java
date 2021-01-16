package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.model.Categorie;

public class CategorieDAOImpl implements CategorieDAO {

	private EntityManager entityManager;

	@Override
	public List<Categorie> list() throws Exception {
		return entityManager.createQuery("from Categorie", Categorie.class).getResultList();
	}

	@Override
	public Categorie get(Long id) throws Exception {
		return entityManager.find(Categorie.class, id);
	}

	@Override
	public void update(Categorie categorieInstance) throws Exception {
		if (categorieInstance == null) {
			throw new Exception("Problema valore in input");
		}
		categorieInstance = entityManager.merge(categorieInstance);
	}

	@Override
	public void insert(Categorie categorieInstance) throws Exception {
		if (categorieInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(categorieInstance);
	}

	@Override
	public void delete(Categorie categorieInstance) throws Exception {
		if (categorieInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(categorieInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
