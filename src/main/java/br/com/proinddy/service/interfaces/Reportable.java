package br.com.proinddy.service.interfaces;

import java.io.ByteArrayOutputStream;

public interface Reportable {
    ByteArrayOutputStream buildJasperReport(String relatorioName, String base, String empresa);
}
