package br.com.proinddy.service;

import br.com.proinddy.models.report.ReportInfo;
import br.com.proinddy.repository.ReportConfigRepository;
import br.com.proinddy.service.interfaces.Reportable;
import net.sf.jasperreports.engine.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ReportUserService implements Reportable{
    @Inject
    ReportConfigRepository reportRepository;

    @ConfigProperty(name = "report.resource.basepath")
    private String basePath;
    @ConfigProperty(name = "report.resource.template")
    private String template;

    @Override
    public ByteArrayOutputStream buildJasperReport(ReportInfo config, String base, String empresa)
    throws JRException{
        JasperReport jasper = JasperCompileManager.compileReport(getPathFromResource(this.template));

        String filePath = this.basePath+"/"+config.getConfig().getConfiguracoes().getFileSQL();

        JasperPrint print = JasperFillManager.fillReport(jasper, setParameters(config), getDataSource(getPathFromResource(filePath), base, empresa));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(print, bos);
        return bos;
    }
    private JRDataSource getDataSource(String filePath, String base, String empresa){
        try{
        var result = reportRepository.getSql(filePath, base, empresa);
            return new JRResultSetDataSource(result);
        }catch (Exception e){
            e.printStackTrace();
            return new JREmptyDataSource();
        }
    }

    private String getPathFromResource(String value){
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(value).getPath();
    }

    private Map setParameters(ReportInfo config){
        Map parameters = new HashMap();
        parameters.put("Nome", config.getNome());
        return parameters;
    }
}
