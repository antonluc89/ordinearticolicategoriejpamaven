package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Categoria;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo articoliInstance) throws Exception {
		if (articoliInstance == null) {
			throw new Exception("Problema valore in input");
		}
		articoliInstance = entityManager.merge(articoliInstance);
	}

	@Override
	public void insert(Articolo articoliInstance) throws Exception {
		if (articoliInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(articoliInstance);
	}

	@Override
	public void delete(Articolo articoliInstance) throws Exception {
		if (articoliInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(articoliInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Articolo> findAllByCategoria(Categoria categorieInput) {
		TypedQuery<Articolo> query = entityManager
				.createQuery("select a FROM Articoli a join a.categorie c where c = :categorie", Articolo.class);
		query.setParameter("categorie", categorieInput);
		return query.getResultList();
	}

}
