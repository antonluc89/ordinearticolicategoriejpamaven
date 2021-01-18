package it.ordinearticolicategoriejpamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	private Set<Articolo> articoli = new HashSet<Articolo>();

	public Categoria() {
	}

	public Categoria(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}

	@Override
	public String toString() {
		return "Categorie [id= " + id + ", descrizione= " + descrizione + "]";
	}

	public void addToArticoli(Articolo articoliInstance) {
		this.articoli.add(articoliInstance);
		articoliInstance.getCategorie().add(this);
	}
	
	public void removeFromArticoli(Articolo articoliInstance) {
		this.articoli.remove(articoliInstance);
		articoliInstance.getCategorie().add(this);
	}
}
