package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.ordinearticolicategoriejpamaven.model.Articoli;
import it.ordinearticolicategoriejpamaven.model.Categorie;

public class ArticoliDAOImpl implements ArticoliDAO {

	private EntityManager entityManager;

	@Override
	public List<Articoli> list() throws Exception {
		return entityManager.createQuery("from Articoli", Articoli.class).getResultList();
	}

	@Override
	public Articoli get(Long id) throws Exception {
		return entityManager.find(Articoli.class, id);
	}

	@Override
	public void update(Articoli articoliInstance) throws Exception {
		if (articoliInstance == null) {
			throw new Exception("Problema valore in input");
		}
		articoliInstance = entityManager.merge(articoliInstance);
	}

	@Override
	public void insert(Articoli articoliInstance) throws Exception {
		if (articoliInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(articoliInstance);
	}

	@Override
	public void delete(Articoli articoliInstance) throws Exception {
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
	public List<Articoli> findAllByCategorie(Categorie categorieInput) {
		TypedQuery<Articoli> query = entityManager
				.createQuery("select a FROM Articoli a join a.categorie c where c = :categorie", Articoli.class);
		query.setParameter("categorie", categorieInput);
		return query.getResultList();
	}

}
