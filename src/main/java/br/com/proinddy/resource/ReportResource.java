package br.com.proinddy.resource;

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

    @Inject
    Reportable reportService;

    @GET
    @Path("")
    @Produces( MediaType.APPLICATION_OCTET_STREAM)
    public Response buildReport(@QueryParam("relatorioName") String relatorioName,
                                @QueryParam("base") String base,
                                @QueryParam("empresa") String empresa
    ){
        ByteArrayOutputStream retorno = reportService.buildJasperReport(relatorioName, base, empresa);
        if(retorno == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(retorno.toByteArray()).build();
    }
}
