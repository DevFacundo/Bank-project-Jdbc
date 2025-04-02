
package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_guia3_banco";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "18346330a";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USERNAME);
            config.setPassword(PASSWORD);
            config.setDriverClassName(DRIVER);

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar la conexión con la base de datos", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
}
}