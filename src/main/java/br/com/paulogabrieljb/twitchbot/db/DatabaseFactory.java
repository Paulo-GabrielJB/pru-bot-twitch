package br.com.paulogabrieljb.twitchbot.db;

import br.com.paulogabrieljb.twitchbot.exceptions.DatabaseException;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;

public class DatabaseFactory {

    private static final Logger LOG = Logger.getLogger(DatabaseFactory.class);
    private static final String DB_URL = "jdbc:sqlite:db.db";
    private static final String DB = "db.db";
    private static Connection conn = null;

    public static void loadDatabase() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            new BufferedReader(new FileReader(DB));

            LOG.info("Try to connect to database.");

            conn = getConnection();

            LOG.info("Connected...");

            LOG.info("Checking if table TB_INTERACT exists...");

            ps = conn.prepareStatement("SELECT count(name) as qtt FROM sqlite_master WHERE type='table' AND name='TB_INTERACT'");
            rs = ps.executeQuery();

            if (rs.next()) {
                LOG.info("Creating TB_INTERACT");
                int qtt = rs.getInt("qtt");
                if (qtt <= 0) {
                    ps = conn.prepareStatement("CREATE TABLE TB_INTERACT (" +
                            " ID_INTERACT INTEGER PRIMARY KEY AUTOINCREMENT," +
                            " DS_MESSAGE VARCHAR(255))");
                    ps.execute();
                }
                }

            rs.close();
            ps.close();

            LOG.info("Checking if table TB_COMMANDS exists...");

            ps = conn.prepareStatement("SELECT name FROM sqlite_master WHERE type='table' AND name='TB_COMMANDS'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                LOG.info("Creating TB_COMMANDS");
                ps = conn.prepareStatement("CREATE TABLE TB_COMMANDS (" +
                        " ID_COMMAND INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " NM_COMMAND VARCHAR(255)," +
                        " DS_COMMAND VARCHAR(255)," +
                        " FL_RANDOM BOOLEAN)");
                ps.execute();
            }

            LOG.info("Base tables successfully checked");

        } catch (IOException e) {
            LOG.info("Database file not existes, creating...");
            createDatabaseFile();
            loadDatabase();
        } catch (SQLException e) {
            LOG.info("Error in DB: " + e.getMessage());
            e.printStackTrace();
        } finally {
            close(rs, ps, null);
        }
    }

    private static void createDatabaseFile() {
        try {
            new BufferedWriter(new FileWriter(DB));
        } catch (IOException e) {
            LOG.info("Error in creating database file");
        }

    }

    public static Connection getConnection() {
        try {
            LOG.info("Try to connect to database.");
            if (conn == null)
                conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            LOG.info("Error to get connection");
        }

        return conn;
    }

    public static void close(ResultSet rs, Statement st, Connection conn){
        try{
            if(rs != null)
                rs.close();
            if(st != null)
                st.close();
            if(conn != null)
                conn.close();
        } catch (SQLException e){
            throw new DatabaseException("Error: " + e.getMessage().toLowerCase());
        }
    }

}
