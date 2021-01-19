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
	public void update(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		articoloInstance = entityManager.merge(articoloInstance);
	}

	@Override
	public void insert(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(articoloInstance);
	}

	@Override
	public void delete(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(articoloInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Articolo> findAllByCategoria(Categoria categoriaInput) {
		TypedQuery<Articolo> query = entityManager
				.createQuery("select a FROM Articolo a join a.categorie c where c = :categoria", Articolo.class);
		query.setParameter("categoria", categoriaInput);
		return query.getResultList();
	}

	@Override
	public Long findAllArticoliByCategoriaECalcolaSomma(Categoria categoriaInput) {
		TypedQuery<Long> query = entityManager
				.createQuery("select SUM(a.prezzoSingolo) FROM Articolo a join a.categorie c where c = :categoria",Long.class);
		query.setParameter("categoria", categoriaInput);
		return query.getSingleResult();
	}
	
	@Override
	public Double findAllArticoliByCategoriaECalcolaIlPrezzoMedio(Categoria categoriaInput) {
		TypedQuery<Double> query = entityManager
				.createQuery("select AVG(a.prezzoSingolo) FROM Articolo a join a.categorie c where c = :categoria",Double.class);
		query.setParameter("categoria", categoriaInput);
		return query.getSingleResult();
	}

	@Override
	public List<Articolo> findAllArticoliWhereForeigKeyOrdineIsNull() {
		TypedQuery<Articolo> query = entityManager
				.createQuery("select a FROM Articolo a left join fetch a.ordine o where o.id IS NULL", Articolo.class);
			return query.getResultList();
	}

}
