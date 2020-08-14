package br.com.paulogabrieljb.twitchbot.dao;

import br.com.paulogabrieljb.twitchbot.model.Generic;

import java.util.List;

public interface InteractDao {

    List<Generic> findAll();
    void deleteById(Long id);
    Generic insert(Generic obj);


}
