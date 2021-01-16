package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.ordinearticolicategoriejpamaven.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categorie", Categoria.class).getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria categorieInstance) throws Exception {
		if (categorieInstance == null) {
			throw new Exception("Problema valore in input");
		}
		categorieInstance = entityManager.merge(categorieInstance);
	}

	@Override
	public void insert(Categoria categorieInstance) throws Exception {
		if (categorieInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(categorieInstance);
	}

	@Override
	public void delete(Categoria categorieInstance) throws Exception {
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
