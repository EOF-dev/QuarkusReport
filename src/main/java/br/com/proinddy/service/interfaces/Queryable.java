package br.com.proinddy.service.interfaces;

import br.com.proinddy.models.report.ReportInfo;

import java.sql.SQLException;

public interface Queryable {
    ReportInfo getById(int id) throws SQLException;
}
