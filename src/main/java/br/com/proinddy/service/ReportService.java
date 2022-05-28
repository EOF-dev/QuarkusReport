package br.com.proinddy.service;

import br.com.proinddy.config.DOA;
import br.com.proinddy.helper.GenericSQL;
import br.com.proinddy.model.ReportDTO;
import br.com.proinddy.model.TemplateValues;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ReportService {

    @ConfigProperty( name = "proindy.report.template")
    private String tamplate;

    @Inject
    DOA doa;

    @Inject
    GenericSQL genericSQL;

    public ByteArrayOutputStream report(ReportDTO reportDTO) throws JRException {
        ClassLoader loader = getClass().getClassLoader();
        URL URL = loader.getResource(this.tamplate);

        List<TemplateValues> templateValues = new ArrayList<>();

        try {
            String sql = genericSQL.parse(reportDTO.scriptName(), reportDTO.dataBaseName(), reportDTO.idCompany());
            //String sql = "select col_nome,col_codigo,col_fone_cel as col_futuro from proinddy_canopus.colaboradores c where col_ativo = 1 and col_empresa= 2 and ( col_nome like '%%'  or col_nome is null ) order by col_nome;";
            //System.out.println(sql);
            ResultSet rs = doa.execute(sql);
            while (rs.next()) {
                TemplateValues template = new TemplateValues(
                        rs.getString("col_nome"),
                        rs.getLong("col_codigo"),
                        rs.getString("col_futuro"));
                templateValues.add(template);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(URL.getPath());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(templateValues));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream;
    }
}
