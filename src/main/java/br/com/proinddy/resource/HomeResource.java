package br.com.proinddy.resource;

import br.com.proinddy.config.DOA;
import br.com.proinddy.helper.GenericSQL;
import br.com.proinddy.service.ReportService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RequestScoped
@Path("/home")
public class HomeResource {

    @Inject
    public HomeResource(ReportService reportService, GenericSQL genericSQL, DOA dao) {
        this.reportService = reportService;
        this.genericSQL = genericSQL;
        this.doa = dao;
    }

    DOA doa;

    private GenericSQL genericSQL;

    private ReportService reportService;

    public static final String VERSION = "1.0-final";

    @GET
    @Path("/info")
    public String info() {
        return VERSION;
    }
}
