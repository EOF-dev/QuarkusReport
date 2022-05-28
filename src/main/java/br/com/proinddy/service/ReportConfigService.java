package br.com.proinddy.service;

import br.com.proinddy.models.report.ReportInfo;
import br.com.proinddy.repository.ReportConfigRepository;
import br.com.proinddy.service.interfaces.Queryable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

@Singleton
public class ReportConfigService implements Queryable {

    @Inject
    ReportConfigRepository reportConfigRepository;

    public ReportInfo getById(int id) throws SQLException {
        return reportConfigRepository.getById(id);
    }
}
