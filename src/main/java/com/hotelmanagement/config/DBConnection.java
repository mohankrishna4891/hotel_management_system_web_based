package com.hotelmanagement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private static final HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
                "jdbc:mysql://localhost:3306/hotel"
        );

        config.setUsername("root");

        config.setPassword("Sennheiser$91");

        config.setDriverClassName(
                "com.mysql.cj.jdbc.Driver"
        );

        config.setMaximumPoolSize(10);

        config.setMinimumIdle(2);

        config.setIdleTimeout(30000);

        config.setMaxLifetime(1800000);

        config.setConnectionTimeout(30000);

        dataSource = new HikariDataSource(config);
    }

    private DBConnection() {
    }

    public static Connection getConnection()
            throws SQLException {

        return dataSource.getConnection();
    }
}
