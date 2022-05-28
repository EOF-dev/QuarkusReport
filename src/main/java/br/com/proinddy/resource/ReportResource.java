package br.com.proinddy.resource;

import br.com.proinddy.config.DOA;
import br.com.proinddy.helper.GenericSQL;
import br.com.proinddy.model.ReportDTO;
import br.com.proinddy.service.ReportService;
import net.sf.jasperreports.engine.JRException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;

@RequestScoped
@Path("/report")
public class ReportResource {

    @Inject
    public ReportResource(ReportService reportService, GenericSQL genericSQL, DOA dao) {
        this.reportService = reportService;
        this.genericSQL = genericSQL;
        this.doa = dao;
    }

    DOA doa;

    private GenericSQL genericSQL;

    private ReportService reportService;

    public static final String VERSION = "1.0-final";

    @POST
    @Produces( MediaType.APPLICATION_OCTET_STREAM)
    public Response getReport(ReportDTO report) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = reportService.report(report);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        Response.ResponseBuilder response = Response.ok(byteArrayOutputStream.toByteArray());
        response.header("Content-Disposition", "inline;filename=" + report.scriptName() + ".pdf");
        response.header("Content-Type", "application/pdf");
        return response.build();
    }
}