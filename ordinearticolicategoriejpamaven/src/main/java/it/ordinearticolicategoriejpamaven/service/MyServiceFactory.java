package it.ordinearticolicategoriejpamaven.service;

import it.ordinearticolicategoriejpamaven.dao.MyDAOFactory;

public class MyServiceFactory {

	private static OrdineService ORDINE_SERVICE_INSTANCE;
	private static ArticoloService ARTICOLI_SERVICE_INSTANCE;
	private static CategoriaService CATEGORIE_SERVICE_INSTANCE;

	public static OrdineService getOrdineServiceInstance() {
		if (ORDINE_SERVICE_INSTANCE == null)
			ORDINE_SERVICE_INSTANCE = new OrdineServiceImpl();

		ORDINE_SERVICE_INSTANCE.setOrdineDAO(MyDAOFactory.getOrdineDAOInstance());
		return ORDINE_SERVICE_INSTANCE;
	}

	public static ArticoloService getArticoliServiceInstance() {
		if (ARTICOLI_SERVICE_INSTANCE == null)
			ARTICOLI_SERVICE_INSTANCE = new ArticoloServiceImpl();

		ARTICOLI_SERVICE_INSTANCE.setArticoliDAO(MyDAOFactory.getArticoliDAOInstance());
		return ARTICOLI_SERVICE_INSTANCE;
	}

	public static CategoriaService getCategorieServiceInstance() {
		if (CATEGORIE_SERVICE_INSTANCE == null)
			CATEGORIE_SERVICE_INSTANCE = new CategoriaServiceImpl();

		CATEGORIE_SERVICE_INSTANCE.setCategorieDAO(MyDAOFactory.getCategorieDAOInstance());
		return CATEGORIE_SERVICE_INSTANCE;
	}

}
