package it.ordinearticolicategoriejpamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articoli")
public class Articoli {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "prezzosingolo")
	private int prezzoSingolo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordine_id")
	private Ordine ordine;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "articoli_categorie", joinColumns = @JoinColumn(name = "articoli_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "categorie_id", referencedColumnName = "ID"))
	Set<Categorie> categorie = new HashSet<Categorie>();

	public Articoli() {
	}

	public Articoli(String descrizione, int prezzoSingolo) {
		super();
		this.descrizione = descrizione;
		this.prezzoSingolo = prezzoSingolo;
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

	public int getPrezzoSingolo() {
		return prezzoSingolo;
	}

	public void setPrezzoSingolo(int prezzoSingolo) {
		this.prezzoSingolo = prezzoSingolo;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Set<Categorie> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categorie> categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {

		return "Aricoli [id= " + id + ", descrizione= " + descrizione + ", prezzoSingolo= " + prezzoSingolo + "]";
	}

	public void addToCategorie(Categorie categorieInstance) {
		this.categorie.add(categorieInstance);
		categorieInstance.getArticoli().add(this);
	}

	public void removeFromCategorie(Categorie categorieInstance) {
		this.categorie.remove(categorieInstance);
		categorieInstance.getArticoli().remove(this);
	}
}