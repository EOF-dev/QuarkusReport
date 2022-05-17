package br.com.proinddy.helper;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Singleton;
import java.io.*;

@Singleton
public class GenericSQL {

    @ConfigProperty(name= "mixtel.report.path")
    private String REPORT_PATH;

    public String parse(String relatorioName, String base, String empresa) throws IOException {
        FileInputStream stream = new FileInputStream(this.REPORT_PATH +"/"+relatorioName+".sql");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String sqlForSelect = "";

        while(bufferedReader.ready()){
            sqlForSelect += bufferedReader.readLine();
        }

        sqlForSelect = sqlForSelect.replaceAll("\\|\\|baseNome\\|\\|",base);
        sqlForSelect = sqlForSelect.replaceAll("\\|\\|emp\\|\\|",empresa);

        return sqlForSelect;
    }

}
