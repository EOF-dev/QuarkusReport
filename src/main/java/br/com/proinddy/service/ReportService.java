package br.com.proinddy.service;

import br.com.proinddy.model.ReportData;
import br.com.proinddy.model.TemplateValues;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.input.ClassLoaderObjectInputStream;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ReportService {

    @ConfigProperty( name = "proindy.report.template")
    private String tamplate;


    public ByteArrayOutputStream report(ReportData reportData) throws JRException {
        ClassLoader loader = getClass().getClassLoader();
        URL URL = loader.getResource(this.tamplate);

        JasperReport jasperReport = JasperCompileManager.compileReport(URL.getPath());
        Map stringMap = new HashMap();
        stringMap.put("Nome", "Template");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        return outputStream;
    }

    private JRDataSource dataSource() {
        List<TemplateValues> data = new ArrayList<>();
        data.add(new TemplateValues("Pedro", 12312312L, "mingual"));
        data.add(new TemplateValues("Felipe", 12234312L, "doisnasemana"));
        data.add(new TemplateValues("Gilmar", 213123L, "jdjfsjio"));
        data.add(new TemplateValues("Cleber", 909954L, "ijhdfj"));
        return new JRBeanCollectionDataSource(data);
    }
}
