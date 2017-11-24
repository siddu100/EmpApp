/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */

package com.developer.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import oracle.jdbc.pool.OracleDataSource;

public class DBConnectionOracleDB {

    private static final String URL = "jdbc:oracle:thin:@";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String DBURL = null;
    //Environment Variable Cloud
    public static final Optional<String> DBAAS_USERNAME = Optional.ofNullable(System.getenv("DBAAS_USER_NAME"));
    public static final Optional<String> DBAAS_PASSWORD = Optional.ofNullable(System.getenv("DBAAS_USER_PASSWORD"));

    public static final Optional<String> DBAAS_DEFAULT_CONNECT_DESCRIPTOR = Optional.ofNullable(System.getenv("DBAAS_DEFAULT_CONNECT_DESCRIPTOR"));
    //public static Optional<String> DBAAS_DEFAULT_CONNECT_DESCRIPTOR = Optional.ofNullable(DBURL);
    
    //Local settings        
    public static final String LOCAL_USERNAME = "oracleusr";
    public static final String LOCAL_PASSWORD = "oracle";
    public static final String LOCAL_DEFAULT_CONNECT_DESCRIPTOR = "141.144.22.10:1521/PDB1.gse00010693.oraclecloud.internal";
    //public static final String LOCAL_DEFAULT_CONNECT_DESCRIPTOR = "192.168.56.101:1521/pdborcl.cn.oracle.com";

    private static Connection connection = null;
    private static DBConnectionOracleDB instance = null;

    private DBConnectionOracleDB() {
        try {
            Class.forName(DRIVER).newInstance();
        } catch (Exception sqle) {
            sqle.getMessage();
        }
    }

    public static DBConnectionOracleDB getInstance() {
        if (connection == null) {
            instance = new DBConnectionOracleDB();
        }
        return instance;
    }


    public Connection getConnection() {
        if (connection == null) {
            try {
                OracleDataSource ods = new OracleDataSource();
                ods.setURL(URL + DBAAS_DEFAULT_CONNECT_DESCRIPTOR.orElse(LOCAL_DEFAULT_CONNECT_DESCRIPTOR));
                ods.setUser(DBAAS_USERNAME.orElse(LOCAL_USERNAME));
                ods.setPassword(DBAAS_PASSWORD.orElse(LOCAL_PASSWORD));
                connection = ods.getConnection();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return connection;
    }
}