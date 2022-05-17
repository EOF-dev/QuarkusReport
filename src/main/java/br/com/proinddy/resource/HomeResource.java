package br.com.proinddy.resource;

import br.com.proinddy.config.DOA;
import br.com.proinddy.helper.GenericSQL;
import net.sf.jasperreports.engine.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@RequestScoped
@Path("/home")
public class HomeResource {

    @Inject
    DOA doa;

    @Inject
    private GenericSQL genericSQL;

    public static final String VERSION = "1.0-final";

    @GET
    @Path("/info")
    public String info() {
        return VERSION;
    }

    @GET
    @Path("/jasper-report")
    @Produces( MediaType.APPLICATION_OCTET_STREAM) // studar
    public Response jasperRepport(String jrxml) {
        try {
            //converte pra jasper file
            JasperReport jasper = JasperCompileManager.compileReport("/home/felipe/JaspersoftWorkspace/MyReports/bin/test.jrxml");

            // retorna relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, null, new JREmptyDataSource());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // gera pd
            JasperExportManager.exportReportToPdfStream(print, outputStream);

            Response.ResponseBuilder response = Response.ok(outputStream.toByteArray());
            response.header("Content-Disposition", "inline;filename=" + "test.pdf");
            response.header("Content-Type", "application/pdf");
            return response.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
