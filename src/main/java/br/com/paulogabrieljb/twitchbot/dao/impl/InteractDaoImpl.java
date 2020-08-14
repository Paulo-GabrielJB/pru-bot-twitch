package br.com.paulogabrieljb.twitchbot.dao.impl;

import br.com.paulogabrieljb.twitchbot.dao.DaoFactory;
import br.com.paulogabrieljb.twitchbot.dao.InteractDao;
import br.com.paulogabrieljb.twitchbot.db.DatabaseFactory;
import br.com.paulogabrieljb.twitchbot.exceptions.DatabaseException;
import br.com.paulogabrieljb.twitchbot.model.Generic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InteractDaoImpl implements InteractDao {

    private Connection conn;

    public InteractDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public List<Generic> findAll(){

        List<Generic> messages = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT ID_INTERACT, DS_MESSAGE FROM TB_INTERACT");
            rs = ps.executeQuery();

            while(rs.next())
                messages.add(instantiate(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseFactory.close(rs, ps, null);
        }

        return messages;
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM TB_INTERACT WHERE ID_INTERACT = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new DatabaseException("Error " + e.getMessage().toLowerCase());
        } finally {
            DatabaseFactory.close(null, ps, null);
        }
    }

    @Override
    public Generic insert(Generic obj) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("INSERT INTO TB_INTERACT (DS_MESSAGE) " +
                    "VALUES(?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, obj.getMessage());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if(rs.next())
                obj.setId(rs.getLong(1));
            return obj;

        } catch (SQLException e){
            throw new DatabaseException("Error: " + e.getMessage().toLowerCase());
        } finally {
            DatabaseFactory.close(rs, ps, null);
        }
    }

    private Generic instantiate(ResultSet rs) throws SQLException{
        Generic obj = new Generic();
        obj.setId(rs.getLong("ID_INTERACT"));
        obj.setMessage(rs.getString("DS_MESSAGE"));
        return obj;
    }

}
