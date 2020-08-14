package br.com.paulogabrieljb.twitchbot.dao;

import br.com.paulogabrieljb.twitchbot.dao.impl.InteractDaoImpl;
import br.com.paulogabrieljb.twitchbot.db.DatabaseFactory;

public class DaoFactory {

    public static InteractDao getInteractDao(){
        return new InteractDaoImpl(DatabaseFactory.getConnection());
    }

}
