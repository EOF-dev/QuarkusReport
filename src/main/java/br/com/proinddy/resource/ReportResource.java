package br.com.proinddy.resource;

import br.com.proinddy.models.report.ReportInfo;
import br.com.proinddy.service.interfaces.Queryable;
import br.com.proinddy.service.interfaces.Reportable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;

@RequestScoped
@Path("/report")
public class ReportResource {

    Reportable reportService;
    Queryable reportConfigService;

    @Inject
    public ReportResource(Reportable reportService, Queryable reportConfigService) {
        this.reportService = reportService;
        this.reportConfigService = reportConfigService;
    }

    @GET
    @Path("/{id}")
    @Produces( MediaType.APPLICATION_OCTET_STREAM)
    public Response buildReport(@PathParam("id") int id,
                                @QueryParam("base") String base,
                                @QueryParam("empresa") String empresa
    ){
        try{
            ReportInfo config = reportConfigService.getById(id);
            if(config == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            ByteArrayOutputStream relatorio = reportService.buildJasperReport(config, base, empresa);

            Response.ResponseBuilder response = Response.ok(relatorio.toByteArray());
            response.header("Content-Disposition", "inline;filename=" + config.getNome() + ".pdf");
            response.header("Content-Type", "application/pdf");
            return response.build();

        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
