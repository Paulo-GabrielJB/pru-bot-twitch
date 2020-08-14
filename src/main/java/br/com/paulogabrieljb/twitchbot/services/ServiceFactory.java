package br.com.paulogabrieljb.twitchbot.services;

import br.com.paulogabrieljb.twitchbot.dao.DaoFactory;
import br.com.paulogabrieljb.twitchbot.services.impl.InteractServiceImpl;

public class ServiceFactory {

    public static InteractService getInteractService(){
        return new InteractServiceImpl(DaoFactory.getInteractDao());
    }

}
