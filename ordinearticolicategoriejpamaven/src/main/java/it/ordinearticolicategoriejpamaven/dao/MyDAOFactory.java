package it.ordinearticolicategoriejpamaven.dao;

public class MyDAOFactory {
	private static OrdineDAO ORDINE_DAO_INSTANCE = null;
	private static ArticoliDAO ARTICOLI_DAO_INSTANCE = null;
	private static CategorieDAO CATEGORIE_DAO_INSTANCE = null;

	public static OrdineDAO getOrdineDAOInstance() {
		if (ORDINE_DAO_INSTANCE == null)
			ORDINE_DAO_INSTANCE = new OrdineDAOImpl();
		return ORDINE_DAO_INSTANCE;
	}

	public static ArticoliDAO getArticoliDAOInstance() {
		if (ARTICOLI_DAO_INSTANCE == null)
			ARTICOLI_DAO_INSTANCE = new ArticoliDAOImpl();
		return ARTICOLI_DAO_INSTANCE;
	}

	public static CategorieDAO getCategorieDAOInstance() {
		if (CATEGORIE_DAO_INSTANCE == null)
			CATEGORIE_DAO_INSTANCE = new CategorieDAOImpl();
		return CATEGORIE_DAO_INSTANCE;
	}

}