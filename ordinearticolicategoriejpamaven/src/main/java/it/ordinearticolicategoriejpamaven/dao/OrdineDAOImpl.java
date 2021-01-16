package it.ordinearticolicategoriejpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.ordinearticolicategoriejpamaven.model.Articolo;
import it.ordinearticolicategoriejpamaven.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.merge(ordineInstance);

	}

	@Override
	public void insert(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(ordineInstance);

	}

	@Override
	public void delete(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(ordineInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> findAllByArticoli(Articolo articoliInput) {
		TypedQuery<Ordine> query = entityManager
				.createQuery("select o FROM Ordine o join o.articoli a where a = :articoli", Ordine.class);
		query.setParameter("articoli", articoliInput);
		return query.getResultList();
	}

}
