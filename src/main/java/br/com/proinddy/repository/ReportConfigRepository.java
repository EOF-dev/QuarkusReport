package br.com.proinddy.repository;

import br.com.proinddy.config.DOA;
import br.com.proinddy.helper.GenericSQL;
import br.com.proinddy.models.report.ReportInfo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class ReportConfigRepository {

    DOA dao;
    GenericSQL sqlHelper;

    @Inject
    public ReportConfigRepository(DOA dao, GenericSQL sqlHelper) {
        this.dao = dao;
        this.sqlHelper = sqlHelper;
    }

    private static final String TABLENAME = "relatorio";

    public ReportInfo getById(int id) throws SQLException {
        ResultSet result = dao.execute(sqlHelper.getById(id, TABLENAME));

        if (result.isBeforeFirst()) {
            result.next();
            ReportInfo reportInfo = new ReportInfo();
            if (!result.wasNull()) {
                reportInfo.setId(result.getLong("rel_id"));
                reportInfo.setNome(result.getString("rel_nome"));
                reportInfo.setGrupoId(result.getLong("grel_id"));
                reportInfo.setConfigString(result.getString("rel_conf"));
                reportInfo.setConfig(reportInfo.getConfigString());

                return reportInfo;
            }
        }
        return null;
    }

    public ResultSet getSql(String filePath, String base, String empresa) throws IOException, SQLException {
        String sql = sqlHelper.parse(filePath, base, empresa);
        return dao.execute(sql);
    }

}
