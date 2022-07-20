package br.com.proinddy.helper;

import javax.inject.Singleton;
import java.io.*;

@Singleton
public class GenericSQL {

    public String parse(String relatorioName, String base, String empresa) throws IOException {
        FileInputStream stream = new FileInputStream(relatorioName);
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
                .replace("||emp||", empresa)
                .replace("||baseNome||", base);
    }

    public String getById(Integer id, String tablename) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM ");
        sql.append(tablename);
        sql.append(" WHERE rel_id = ||id||");

        return sql.toString().replace("||id||", id.toString());
    }
}
