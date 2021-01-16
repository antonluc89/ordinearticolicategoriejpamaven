package it.ordinearticolicategoriejpamaven.dao;

public class MyDAOFactory {
	private static OrdineDAO ORDINE_DAO_INSTANCE = null;
	private static ArticoloDAO ARTICOLI_DAO_INSTANCE = null;
	private static CategoriaDAO CATEGORIE_DAO_INSTANCE = null;

	public static OrdineDAO getOrdineDAOInstance() {
		if (ORDINE_DAO_INSTANCE == null)
			ORDINE_DAO_INSTANCE = new OrdineDAOImpl();
		return ORDINE_DAO_INSTANCE;
	}

	public static ArticoloDAO getArticoliDAOInstance() {
		if (ARTICOLI_DAO_INSTANCE == null)
			ARTICOLI_DAO_INSTANCE = new ArticoloDAOImpl();
		return ARTICOLI_DAO_INSTANCE;
	}

	public static CategoriaDAO getCategorieDAOInstance() {
		if (CATEGORIE_DAO_INSTANCE == null)
			CATEGORIE_DAO_INSTANCE = new CategoriaDAOImpl();
		return CATEGORIE_DAO_INSTANCE;
	}

}