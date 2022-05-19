package br.com.proinddy.service;

import br.com.proinddy.config.DOA;
import br.com.proinddy.exception.SqlFileNotFoundException;
import br.com.proinddy.helper.GenericSQL;
import br.com.proinddy.service.interfaces.Reportable;
import net.sf.jasperreports.engine.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Singleton
public class ReportUserService implements Reportable {

    DOA dao;
    GenericSQL genericSQL;

    @Inject
    public ReportUserService(DOA dao, GenericSQL genericSQL) {
        this.dao = dao;
        this.genericSQL = genericSQL;
    }

    @ConfigProperty(name= "proindy.report.path")
    private String reportPath;

    @ConfigProperty(name = "proindy.report.template")
    private String reportTemplate;

    public ByteArrayOutputStream buildJasperReport(String relatorioName, String base, String empresa){
        try{
            JasperReport jasper = JasperCompileManager.compileReport(getPathFromResource(this.reportTemplate));
            JasperPrint print = JasperFillManager.fillReport(jasper, null, getDataSource(relatorioName, base, empresa));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, bos);
            return bos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private JRDataSource getDataSource(String relatorioName, String base, String empresa){
        String sql = getSql(relatorioName, base, empresa);
        try{
            return new JRResultSetDataSource(dao.execute(sql));
        }catch (Exception e){
            e.printStackTrace();
            return new JREmptyDataSource();
        }
    }

    private String getSql(String relatorioName, String base, String empresa){
        String validName = validReportName(relatorioName);
        try{
            if(validName == null){
                throw new SqlFileNotFoundException();
            }
            return genericSQL.parse(validName, base, empresa);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String validReportName(String reportName){
        File file = new File(getPathFromResource(this.reportPath) +"/"+reportName+".sql");
        return file.exists() ? file.getName() : null;
    }

    private String getPathFromResource(String value){
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(value).getPath();
    }
}
