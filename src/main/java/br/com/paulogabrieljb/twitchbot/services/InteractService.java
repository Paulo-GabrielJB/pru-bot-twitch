package br.com.paulogabrieljb.twitchbot.services;

import br.com.paulogabrieljb.twitchbot.model.Generic;

import java.util.List;

public interface InteractService {

    List<Generic> findAll();
    Generic insert(Generic obj);
    void deleteById(Long id);

}
