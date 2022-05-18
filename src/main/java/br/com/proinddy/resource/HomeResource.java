package br.com.proinddy.resource;

import br.com.proinddy.config.DOA;
import br.com.proinddy.helper.GenericSQL;
import br.com.proinddy.template.model.TemplateValues;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            JasperReport jasper = JasperCompileManager.compileReport("/home/felipe/Projects/EOF/QuarkusReport/src/main/java/br/com/proinddy/template/test.jrxml");

            // retorna relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, null, dataSource());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // gera pdf
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

    private JRDataSource dataSource() {
        List<TemplateValues> data = new ArrayList<>();
        data.add(new TemplateValues("campo 1"));
        data.add(new TemplateValues("campo 2"));
        return new JRBeanCollectionDataSource(data);
    }
}
