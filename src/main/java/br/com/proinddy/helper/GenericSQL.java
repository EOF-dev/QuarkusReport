package br.com.proinddy.helper;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Singleton;
import java.io.*;

@Singleton
public class GenericSQL {

    @ConfigProperty(name= "proindy.report.path")
    private String reportPath;

    public String parse(String relatorioName, String base, String empresa) throws IOException {
        FileInputStream stream = new FileInputStream(this.reportPath +"/"+relatorioName+".sql");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder sqlForSelect = new StringBuilder();

        try {
            while (bufferedReader.ready()) {
                sqlForSelect.append(bufferedReader.readLine());
            }
        } finally {
            bufferedReader.close();
        }

        return sqlForSelect.toString()
                .replace("\\|\\|baseNome\\|\\|",base)
                .replace("\\|\\|emp\\|\\|",empresa);

    }

}
