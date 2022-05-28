package br.com.proinddy.service.interfaces;

import br.com.proinddy.models.report.ReportInfo;
import net.sf.jasperreports.engine.JRException;

import java.io.ByteArrayOutputStream;

public interface Reportable {
    ByteArrayOutputStream buildJasperReport(ReportInfo config, String base, String empresa) throws JRException;
}
