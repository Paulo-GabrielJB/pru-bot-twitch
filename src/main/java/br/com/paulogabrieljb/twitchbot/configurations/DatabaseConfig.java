package br.com.paulogabrieljb.twitchbot.configurations;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final Logger LOG = Logger.getLogger(DatabaseConfig.class);
    private static final String DB_URL = "jdbc:sqlite:/db.db";
    private static final String DB = "db.db";
    private static Connection conn = null;

    public static final Connection loadDatabase(){

        try {

            new BufferedReader(new FileReader(DB));

            LOG.info("Try to connect to database.");
            if(conn == null)
                conn = DriverManager.getConnection(DB_URL);

            LOG.info("Connected...");

        } catch (IOException e){
            LOG.info("Database file not existes, creating...");
            createDatabaseFile();
            loadDatabase();
        } catch (SQLException e){
            LOG.info("Can't connect to DB");
            e.printStackTrace();
        }
        return conn;
    }

    private static final void createDatabaseFile() {
        try{
            new BufferedWriter(new FileWriter(DB));
        } catch (IOException e){
            LOG.info("Error in creating database file");
        }

    }

}
