package br.com.paulogabrieljb.twitchbot.repositories;

import br.com.paulogabrieljb.twitchbot.configurations.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {

    private Connection conn;

    public MessageRepository(Connection conn) {
        this.conn = conn;
    }

    public List<String> findAll(){

        List<String> messages = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT DS_MESSAGE FROM TB_INTERACT");
            rs = ps.executeQuery();

            while(rs.next())
                messages.add(rs.getString("DS_MESSAGE"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
            } catch (SQLException e){
                System.out.println("Error " + e.getMessage());
            }
        }

        return messages;
    }

}
