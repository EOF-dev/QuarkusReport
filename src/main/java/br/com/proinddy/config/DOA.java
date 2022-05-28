package br.com.proinddy.config;

import io.agroal.api.AgroalDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
public class DOA {

    AgroalDataSource datasource;

    @Inject
    public DOA(AgroalDataSource datasource) {
        this.datasource = datasource;
    }

    public ResultSet execute(String sql) throws SQLException {
        return getStatement().executeQuery(sql);
    }

    public boolean executeUpdate(String sql) throws SQLException {
        return getStatement().execute(sql);
    }

    private Statement getStatement() throws SQLException {
        Connection connection = this.datasource.getConnection();
        return connection.createStatement();
    }


}
