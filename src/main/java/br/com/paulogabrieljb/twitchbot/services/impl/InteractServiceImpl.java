package br.com.paulogabrieljb.twitchbot.services.impl;

import br.com.paulogabrieljb.twitchbot.dao.InteractDao;
import br.com.paulogabrieljb.twitchbot.db.DatabaseFactory;
import br.com.paulogabrieljb.twitchbot.exceptions.DatabaseException;
import br.com.paulogabrieljb.twitchbot.model.Generic;
import br.com.paulogabrieljb.twitchbot.services.InteractService;
import org.apache.log4j.Logger;

import java.util.List;

public class InteractServiceImpl implements InteractService {

    private static final Logger LOG = Logger.getLogger(InteractServiceImpl.class);

    private final InteractDao dao;

    public InteractServiceImpl(final InteractDao dao){
        this.dao = dao;
    }

    @Override
    public List<Generic> findAll() {

        List<Generic> listObj = null;
        try{
            listObj = dao.findAll();
        } catch (DatabaseException e){
            LOG.error(String.format("Error %s", e.getMessage()), e);
        }
        return listObj;
    }

    @Override
    public Generic insert(Generic obj) {

        if(obj.getMessage() == null || obj.getMessage().trim().equals(""))
            throw new IllegalArgumentException("Message not be empty or null");

        try {
            dao.insert(obj);
        } catch (DatabaseException e){
            LOG.error(String.format("Error %s", e.getMessage()), e);
        }

        return obj;
    }

    @Override
    public void deleteById(Long id) {
        if(id == null || id <= 0)
            throw new IllegalArgumentException("Invalid id!");
        try {
            dao.deleteById(id);
        } catch (DatabaseException e){
            LOG.error(String.format("Error %s", e.getMessage()), e);
        }
    }
}
