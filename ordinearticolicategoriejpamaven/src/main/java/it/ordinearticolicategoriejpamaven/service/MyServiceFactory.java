package it.ordinearticolicategoriejpamaven.service;

import it.ordinearticolicategoriejpamaven.dao.MyDAOFactory;

public class MyServiceFactory {

	private static OrdineService ORDINE_SERVICE_INSTANCE;
	private static ArticoliService ARTICOLI_SERVICE_INSTANCE;
	private static CategorieService CATEGORIE_SERVICE_INSTANCE;

	public static OrdineService getOrdineServiceInstance() {
		if (ORDINE_SERVICE_INSTANCE == null)
			ORDINE_SERVICE_INSTANCE = new OrdineServiceImpl();

		ORDINE_SERVICE_INSTANCE.setOrdineDAO(MyDAOFactory.getOrdineDAOInstance());
		return ORDINE_SERVICE_INSTANCE;
	}

	public static ArticoliService getArticoliServiceInstance() {
		if (ARTICOLI_SERVICE_INSTANCE == null)
			ARTICOLI_SERVICE_INSTANCE = new ArticoliServiceImpl();

		ARTICOLI_SERVICE_INSTANCE.setArticoliDAO(MyDAOFactory.getArticoliDAOInstance());
		return ARTICOLI_SERVICE_INSTANCE;
	}

	public static CategorieService getCategorieServiceInstance() {
		if (CATEGORIE_SERVICE_INSTANCE == null)
			CATEGORIE_SERVICE_INSTANCE = new CategorieServiceImpl();

		CATEGORIE_SERVICE_INSTANCE.setCategorieDAO(MyDAOFactory.getCategorieDAOInstance());
		return CATEGORIE_SERVICE_INSTANCE;
	}

}
